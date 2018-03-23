package com.nokia.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;




@Configuration
@EnableWebSecurity
@PropertySource(value={"classpath:ldap.properties"})
public class SpringSecurityLDAPConfig extends WebSecurityConfigurerAdapter{


	@Autowired
	private Environment environment;
	
	@Bean
	public CustomLdapAuthenticationProvider getLDAPAuthenticationProvider(){
		return new CustomLdapAuthenticationProvider();
	}
	
	
	@Bean
    public LdapContextSource contextSource () {
        LdapContextSource contextSource= new LdapContextSource();
        contextSource.setUrl(environment.getRequiredProperty("ldap.url"));
        contextSource.setBase(environment.getRequiredProperty("ldap.base"));
        contextSource.setUserDn(environment.getRequiredProperty("ldap.user"));
        contextSource.setPassword(environment.getRequiredProperty("ldap.password"));
        return contextSource;
    }

    @Bean(name="ldapTemplate")
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());        
    }

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {        
		 auth
	        .eraseCredentials(false)
	        .authenticationProvider(getLDAPAuthenticationProvider());
        
    }	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		 http
         .csrf().disable()
         
            	.logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
         .and()
         		.formLogin().usernameParameter("user").passwordParameter("password")
         		.loginPage("/").failureUrl("/").loginProcessingUrl("/spring")
         .and()
                 .sessionManagement()
                         .invalidSessionUrl("/")
         .and()
                 .authorizeRequests()                        
                 		 .antMatchers("/bower_components/**","/app/styles/**","/app/scripts/**/**","/app/images/**","/app/views/**/**","/app/app.js").permitAll()
                         .antMatchers("/index.html","/").permitAll()
                         .antMatchers("/favicon.ico").permitAll()                         
                         .anyRequest().authenticated();		 		 
		 
		
	}
}
