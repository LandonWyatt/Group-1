package com.mphasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mphasis.model.User;
import com.mphasis.service.UserService;

/*
 * Controller that will redirect all mappings for any login related method calls
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/*
	 * Retrieves login page for user
	 */
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	/*
	 * Check if user exists and logs them in with their credentials
	 */
	@PostMapping("/login")
	public String checkLoginPage(@ModelAttribute User user, Model model, final RedirectAttributes redirectAttributes) {
		String firstName = userService.validateUser(user); // tweak later, simply checks if user exists right now
		if(firstName != null) { 
			
			model.addAttribute("firstName", firstName);
			redirectAttributes.addAttribute("firstName", firstName);
			return "redirect:/product";
		}
			
		return "login";
	}
	
}
