package com.mphasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mphasis.model.User;
import com.mphasis.service.UserService;

/*
 * Controller that will redirect all mappings for any sign up related method calls
 */
@Controller
public class SignUpController {
	
	@Autowired
	private UserService userService;
	
	/*
	 * Redirects user to sign up page
	 */
	@GetMapping("/signup") 
	public String getSignup(Model model){
		model.addAttribute("user", new User());
		return "signup";
	}
	 
	/*
	 * Retrieves information of new user and registers them in the DB
	 */
	@PostMapping("/signup")
	public String signUpUserPage(@ModelAttribute User user, Model model) {
		System.out.println("register button code will go here ");
		System.out.println("FirstName" + user.getFirstName());
		
		if(userService.checkIfUserExists(user.getEmail())) {
			System.out.println("User exists");
			model.addAttribute("errorMessage", "User with emailid exits");
			return "signup";
			
		}else {
			userService.addUser(user); //calling a user service to add a user 
			return "login";
		}
		
		
	}
	
}


