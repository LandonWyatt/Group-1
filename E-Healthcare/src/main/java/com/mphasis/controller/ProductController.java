package com.mphasis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.mphasis.model.Product;
import com.mphasis.service.ProductService;

public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public String getAllProducts() {
		List<Product> productList= productService.getAllProducts();
		return "Product";
	}

}
