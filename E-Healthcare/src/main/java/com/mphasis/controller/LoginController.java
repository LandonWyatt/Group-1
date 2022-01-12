package com.mphasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mphasis.service.LoginService;

public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "Login";
	}
	
	@PostMapping("/login")
	public String checkLoginPage(Model Model) {
		
		boolean checklogin = loginService.checkLogin(null, null);
		return "Login";
	}
	
}
