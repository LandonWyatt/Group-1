package com.mphasis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mphasis.model.Product;
import com.mphasis.service.ProductService;

/*
 * Controller that will perform any function needed for web controllers
 */
@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	private String sortedSearch = ""; // set search parameter to be used
	
	/*
	 * Retrieves all products
	 */
	@GetMapping("/products")
	public List<Product> getAllProducts(Model model) {
		System.out.println("I am here" + model.getAttribute("firstName"));
		return productService.getAllProducts();
	}

	/*
	 * Retrieves a specific product by ID
	 */
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") Long id) {
		return productService.getProduct(id).get();
	}
	
	/*
	 * Retrieves a specific list of product entries
	 *  - Will determine based on current organization and search string
	 */
	@GetMapping("/productSearch")
	public List<Product> getAllProductSearch(String str) {
		List<Product> sortedList = new ArrayList<>();
		switch(sortedSearch) {
			case "id":
				productService.getAllProductSearch(str).stream()
					.sorted((prod1, prod2) -> prod1.getId().compareTo(prod2.getId()))
					.forEach(sortedList::add);
				break;
			case "name":
				productService.getAllProductSearch(str).stream()
					.sorted((prod1, prod2) -> prod1.getName().compareTo(prod2.getName()))
					.forEach(sortedList::add);
				break;
			case "brand":
				productService.getAllProductSearch(str).stream()
					.sorted((prod1, prod2) -> prod1.getBrand().compareTo(prod2.getBrand()))
					.forEach(sortedList::add);
				break;
			case "qty":
				productService.getAllProductSearch(str).stream()
					.sorted((prod1, prod2) -> ((Long)prod1.getQtyAvailable()).compareTo((Long)prod2.getQtyAvailable()))
					.forEach(sortedList::add);
				break;
			case "price":
				productService.getAllProductSearch(str).stream()
					.sorted((prod1, prod2) -> ((Double)prod1.getPrice()).compareTo((Double)prod2.getPrice()))
					.forEach(sortedList::add);
				break;
			default:
				productService.getAllProductSearch(str).stream().forEach(sortedList::add);
		}
		return sortedList;
	}
	
	/*
	 * Receives search string from JS fetch within HTML, then sets how to sort entries
	 */
	@PostMapping("/productSearch/{sort}")
	public void setSortProductSearch(@PathVariable("sort") String sort) {
		sortedSearch = sort;
	}
	
	/*
	 * Receives ID from JS fetch within HTML, then activates or deactivates a product
	 */
	@PutMapping("/productActive/{id}")
	public void updateProductActive(@PathVariable("id") Long id) {
		Product product = productService.getProduct(id).get();
		if(product.isActivate())
			product.setActivate(false);
		else
			product.setActivate(true);
		productService.updateProduct(id, product);
	}
	
	/*
	 * Adds a product based on RequestBody
	 */
	@PostMapping("/products")
	public void addProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}
	
	/*
	 * Updates a product based on RequestBody and ID given
	 */
	@PutMapping("/products/{id}")
	public void updateProduct(@RequestBody Product product, @PathVariable("id") Long id) {
		productService.updateProduct(id, product);
	}
	
	/*
	 * Deletes a product based on ID given
	 */
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
	}

}
