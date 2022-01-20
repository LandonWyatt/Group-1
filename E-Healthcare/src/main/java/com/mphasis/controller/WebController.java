package com.mphasis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mphasis.model.Product;

@Controller
public class WebController {
	
	ProductController productController;
	
	@GetMapping("/")
	public String getHome() {
		return "index";
	}
	
	@GetMapping("/about")
	public String getAbout() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String getContact() {
		return "contact";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		return "signup";
	}

}
