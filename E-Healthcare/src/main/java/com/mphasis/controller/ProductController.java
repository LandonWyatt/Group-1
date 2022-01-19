package com.mphasis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.model.Product;
import com.mphasis.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/productSearch")
	public List<Product> getAllProductSearch(String str) {
		return productService.getAllProductSearch(str);
	}
	
	@PutMapping("/productActive/{id}")
	public void updateProductActive(@PathVariable("id") Long id) {
		Product product = productService.getProduct(id).get();
		if(product.isActivate())
			product.setActivate(false);
		else
			product.setActivate(true);
		productService.updateProduct(id, product);
	}
	
	@PostMapping("/products")
	public void addProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}

}
