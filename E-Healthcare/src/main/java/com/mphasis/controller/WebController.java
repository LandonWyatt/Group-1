package com.mphasis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
	
	ProductController productController;
	
	@GetMapping("/")
	public String getHome() {
		return "index";
	}
	
	@GetMapping("/about")
	public String getAbout(@RequestParam(required = false) String firstName, Model model) {
		model.addAttribute("firstName", firstName);
		return "about";
	}
	
	@GetMapping("/contact")
	public String getContact(@RequestParam(required = false) String firstName, Model model) {
		model.addAttribute("firstName", firstName);
		return "contact";
	}
	
	@GetMapping("/logout")
	public String getlogout() {
		return "index";
	}
	
	/*
	 * @GetMapping("/login") public String getLogin() { return "login"; }
	 */
	
	/*
	 * @GetMapping("/signup") public String getSignup() { return "signup"; }
	 */
	
	

}
