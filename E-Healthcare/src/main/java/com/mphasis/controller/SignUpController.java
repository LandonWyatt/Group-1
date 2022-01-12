package com.mphasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mphasis.entity.User;
import com.mphasis.service.SignUpService;

public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	@GetMapping("/signup")
	public String showSignUpPage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUpUserPage(Model Model) {
		
		User user = new User();
		
		boolean checklogin = signUpService.signUpUser(user);
		return "login";
	}

}
