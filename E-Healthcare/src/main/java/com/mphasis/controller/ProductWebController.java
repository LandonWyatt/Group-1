package com.mphasis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.mphasis.model.Product;

@Controller
public class ProductWebController {
	
	@Autowired
	private ProductController productController;
	private int numEntries = 5;
	private String searchStr = "";
	
	@GetMapping("/product")
	public ModelAndView getProducts(Map<String, Object> model) {
		System.out.println("/product mapping visited");
		List<Product> productsList;
		
		if(searchStr == "") // if there is no value in search bar, simply retrieve entire table
			productsList = productController.getAllProducts();
		else
			productsList = productController.getAllProductSearch(searchStr);
		
		model.put("numChosen", numEntries);
		model.put("products", productsList);
		// user_product or admin_product needs to be determined and decide which to return
		return new ModelAndView("admin_product");
	}
	
	@GetMapping("/productChangeEntries")
	public ModelAndView getProductChangeEntries(Map<String, Object> model) {
		List<Product> productsList;
		
		if(searchStr == "") // if there is no value in search bar, simply retrieve entire table
			productsList = productController.getAllProducts();
		else
			productsList = productController.getAllProductSearch(searchStr);
		
		model.put("numChosen", numEntries);
		model.put("products", productsList);
		// user_product or admin_product needs to be determined and decide which to return
		return new ModelAndView("admin_product_table.html :: product");
	}
	
	@PostMapping("/productChangeEntries")
	public ModelAndView postProductChangeEntries(Map<String, Object> model, @RequestBody Map<String, String> data) {
		numEntries = Integer.parseInt(data.get("numEntries"));
		return new ModelAndView("redirect:/productChangeEntries");
	}
	
	@GetMapping("/productChangeSearch")
	public ModelAndView getProductChangeSearch(Map<String, Object> model) {
		List<Product> productsList;
		
		if(searchStr == "") // if there is no value in search bar, simply retrieve entire table
			productsList = productController.getAllProducts();
		else
			productsList = productController.getAllProductSearch(searchStr);
		
		model.put("numChosen", numEntries);
		model.put("products", productsList);
		// user_product or admin_product needs to be determined and decide which to return
		return new ModelAndView("admin_product_table.html :: product");
	}
	
	@PostMapping("/productChangeSearch")
	public ModelAndView postProductChangeSearch(Map<String, Object> model, @RequestBody Map<String, String> data) {
		searchStr = data.get("searchStr");
		return new ModelAndView("redirect:/productChangeSearch");
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
		return "redirect:/product";
	}
	
}
