package com.mphasis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.mphasis.entity.Products;
import com.mphasis.service.ProductsService;


public class ProductsController {
	
	@Autowired
	private ProductsService productsService;
	
	@GetMapping("/products")
	public String getAllProducts()
	{
		List<Products> productList= productsService.getAllProducts();

		
		return "Product";
	}
	

}
