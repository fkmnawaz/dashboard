
package com.nokia.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nokia.dashboard.service.LoginService;


@Service
public class LoginServiceImpl  implements LoginService{

	@Autowired
	private LdapTemplate ldapTemplate;			
	
	private final Logger m_logger = LoggerFactory.getLogger(this.getClass());


	@Override
	public Authentication authenticate(String userName, String passwd) {
		try {
			ldapTemplate.afterPropertiesSet();
			boolean result = ldapTemplate.authenticate("", "(uid=" + userName + ")", passwd);								
			if (result) {			
				m_logger.info("Successfully logged in through LDAP csl="+userName);
				
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();				
					m_logger.info("WELCOME LDAP USER");
					authorities.add(new SimpleGrantedAuthority("USER"));				
				return new UsernamePasswordAuthenticationToken(userName, passwd, authorities);								
			} else {
				m_logger.error("log in UnSuccessful through LDAP for csl="+userName);
				return null;
			} 
		} catch (Exception e) {
			m_logger.error("log in UnSuccessful through LDAP for csl="+userName);
			return null;
		}
	}
	
	@Override
	public String getCurrentUser() {
		
		SecurityContext context = SecurityContextHolder.getContext();
		if(context.getAuthentication()!=null){
			return context.getAuthentication().getPrincipal().toString();
		}else{
			m_logger.debug("No Authentication");
		}
		return null;
	}
	

}
