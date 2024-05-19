package com.springBoot.security_project_LDAP.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class FeatureController {
	
	@GetMapping("/api-1")
	public String api1(){
		return "Api endpoint - 1";
	}
	
	@GetMapping("/api-2")
	public String api2(){
		return "Api endpoint - 2";
	}

}
