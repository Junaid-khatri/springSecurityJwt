package com.springBoot.security_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.security_project.entity.User;
import com.springBoot.security_project.model.request.SingupRequest;
import com.springBoot.security_project.service.AuthService;
import com.springBoot.security_project.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	JwtService jwtService;
	
	@Autowired 
	AuthService service;
	
	@PostMapping("/signup")
	public ResponseEntity<User> save(@RequestBody SingupRequest request) throws Exception{
		return ResponseEntity.ok(service.saveUser(request));
	}
	@PostMapping("/signin")
	public ResponseEntity<String> login(@RequestBody SingupRequest request) {
		User user = service.authenticate(request);
		return ResponseEntity.ok(jwtService.genrateToken(user));
	}
	

}
