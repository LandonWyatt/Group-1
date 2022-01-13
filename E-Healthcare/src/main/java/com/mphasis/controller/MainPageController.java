package com.mphasis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class MainPageController {
	
	@RequestMapping("/")
	public String homePage() {
		return "index";
	}

}
