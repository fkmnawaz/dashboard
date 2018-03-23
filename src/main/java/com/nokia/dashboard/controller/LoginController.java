package com.nokia.dashboard.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.dashboard.service.LoginService;
import com.nokia.dashboard.vo.CredentialsVO;

@RestController
public class LoginController {

	private final Logger m_logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService m_loginService;	
		
	@RequestMapping(value="login", method=RequestMethod.POST)
	public Authentication login(@RequestBody CredentialsVO credential){	
		return m_loginService.authenticate(credential.getUserName(),credential.getPassword());		
	}
	
	@RequestMapping( value="user", method=RequestMethod.GET)
	public CredentialsVO user(Principal user) {		
		CredentialsVO co=new CredentialsVO();
		co.setUserName(m_loginService.getCurrentUser());
		co.setRole("user");
		return co;
	}

	
}