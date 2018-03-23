package com.nokia.dashboard.service;

import org.springframework.security.core.Authentication;

public interface LoginService {

	Authentication authenticate(String userName,String passwd);	
	
	String getCurrentUser();

}
