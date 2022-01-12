package com.mphasis.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class MainPageController {
	
	@RequestMapping("/")
	public String homePage() {
		return "index";
	}

}
