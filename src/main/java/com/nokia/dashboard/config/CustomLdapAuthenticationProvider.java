package com.nokia.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.nokia.dashboard.service.LoginService;



public class CustomLdapAuthenticationProvider implements AuthenticationProvider {

	
	@Autowired
	private LoginService loginService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		final String name = authentication.getName();
		final String password = authentication.getCredentials().toString();
		if(name != null && password != null){
			Authentication authenticate = loginService.authenticate(name, password);
			return authenticate;
		}else{
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}


}
