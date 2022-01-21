package com.mphasis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Controller that will redirect any overall method calls to different pages
 */
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
	public String getLogout() {
		return "index";
	}
	
	@GetMapping("/checkout")
	public String getCheckout() {
		return "checkout";
	}

}
