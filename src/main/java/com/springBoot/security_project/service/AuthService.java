package com.springBoot.security_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springBoot.security_project.entity.User;
import com.springBoot.security_project.model.SingupRequest;
import com.springBoot.security_project.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authManager;
	
	public User saveUser(SingupRequest request) {
		User user = User.builder()
				.name(request.getName())
				.password(passwordEncoder.encode(request.getPassword())).build();
		return repository.save(user);
	}
	public User authenticate(SingupRequest request) {
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getName(), 
						request.getPassword())
				);
		return repository.findByName(request.getName()).get();
	}

}
