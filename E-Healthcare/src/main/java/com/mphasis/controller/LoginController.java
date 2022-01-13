package com.mphasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mphasis.service.UserService;

public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String checkLoginPage(Long id) {
		boolean checklogin = userService.checkUser(id); // tweak later, simply checks if user exists right now
		
		return "login";
	}
	
}
