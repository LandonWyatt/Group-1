package com.mphasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.model.User;
import com.mphasis.service.UserService;

@RestController
public class SignUpController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public String showSignUpPage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUpUserPage(User user) {
//		boolean checklogin = userService.addUser(user);
		return "login";
	}

}
