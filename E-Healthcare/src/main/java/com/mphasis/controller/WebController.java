package com.mphasis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String getHome() {
		return "index";
	}
	
	@GetMapping("/about")
	public String getAbout() {
		return "about";
	}
	
	@GetMapping("/product")
	public String getProduct() {
		// user_product or admin_product
		return "user_product";
	}
	
	@GetMapping("/contact")
	public String getContact() {
		return "contact";
	}
	
	/*
	 * @GetMapping("/login") public String getLogin() { return "login"; }
	 */
	
	/*
	 * @GetMapping("/signup") public String getSignup() { return "signup"; }
	 */

}
