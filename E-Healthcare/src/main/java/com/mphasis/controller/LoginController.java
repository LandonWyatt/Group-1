package com.mphasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mphasis.model.User;
import com.mphasis.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		model.addAttribute("user", new User());
		System.out.println("Inside login controller");
		return "login";
	}
	
	@PostMapping("/login")
	public String checkLoginPage(@ModelAttribute User user) {
	Boolean checklogin = userService.validateUser(user); // tweak later, simply checks if user exists right now
	if(checklogin) {
		return "redirect:/product";
	}
		
	return "login";
	}
	
}
