package com.mphasis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mphasis.model.Product;

@Controller
public class WebController {
	
	@Autowired
	private ProductController productController;
	
	@GetMapping("/")
	public String getHome() {
		return "index";
	}
	
	@GetMapping("/about")
	public String getAbout() {
		return "about";
	}
	
	@GetMapping("/product")
	public String getProduct(Model model) {
		// user_product or admin_product needs to be determined and decide which to return
//		int numEntries = 5;
		System.out.println("change occurred");
		List<Product> productsList = productController.getAllProducts();
		model.addAttribute("products", productsList);
		return "admin_product";
	}
	
	@GetMapping("/add_product")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "add_product";
	}
	
	@PostMapping("/save_product")
	public String saveProduct(@ModelAttribute("product") Product product) {
		productController.addProduct(product);
		return "redirect:/";
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
