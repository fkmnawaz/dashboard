package com.nokia.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages="com.nokia.*")
@EnableScheduling
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer{
	
	public static void main(String[] args) throws Exception{
		ConfigurableApplicationContext context =SpringApplication.run(Application.class, args);		
	}
		
} 