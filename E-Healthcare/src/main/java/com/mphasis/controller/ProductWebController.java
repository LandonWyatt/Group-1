package com.mphasis.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// Testing value, determine based on user
	private boolean admin = false;
	
	@GetMapping("/product")
	public ModelAndView getProducts(Map<String, Object> model) {
		System.out.println("/product mapping visited");
		List<Product> productsList = productController.getAllProductSearch(searchStr);
		
		if(!admin)
			productsList = productsList.stream().filter((Product prod) -> {return prod.isActivate();}).collect(Collectors.toList());
		
		model.put("numChosen", numEntries);
		model.put("products", productsList);
		// user_product or admin_product needs to be determined and decide which to return
		if(admin)
			return new ModelAndView("admin_product");
		else
			return new ModelAndView("user_product");
	}
	
	@GetMapping("/productChangeEntries")
	public ModelAndView getProductChangeEntries(Map<String, Object> model) {
		List<Product> productsList = productController.getAllProductSearch(searchStr);
		
		if(!admin)
			productsList = productsList.stream().filter((Product prod) -> {return prod.isActivate();}).collect(Collectors.toList());
		
		model.put("numChosen", numEntries);
		model.put("products", productsList);
		// user_product or admin_product needs to be determined and decide which to return
		if(admin)
			return new ModelAndView("admin_product_table.html :: product");
		else
			return new ModelAndView("user_product_table.html :: product");
	}
	
	@PostMapping("/productChangeEntries")
	public ModelAndView postProductChangeEntries(Map<String, Object> model, @RequestBody Map<String, String> data) {
		numEntries = Integer.parseInt(data.get("numEntries"));
		return new ModelAndView("redirect:/productChangeEntries");
	}
	
	@GetMapping("/productChangeSearch")
	public ModelAndView getProductChangeSearch(Map<String, Object> model) {
		List<Product> productsList = productController.getAllProductSearch(searchStr);
		
		if(!admin)
			productsList = productsList.stream().filter((Product prod) -> {return prod.isActivate();}).collect(Collectors.toList());
		
		model.put("numChosen", numEntries);
		model.put("products", productsList);
		// user_product or admin_product needs to be determined and decide which to return
		if(admin)
			return new ModelAndView("admin_product_table.html :: product");
		else
			return new ModelAndView("user_product_table.html :: product");
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
	
	@GetMapping("/update_product/{id}")
	public String updateProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product",productController.getProduct(id));
		return "update_product";
	}
	
	@PostMapping("/save_update")
	public String saveUpdateProduct(@ModelAttribute("product") Product product) {
		productController.updateProduct(product, product.getId());
		return "redirect:/product";
	}
	
	/* Not implemented yet in page */
	@PostMapping("/delete_products/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		productController.deleteProduct(id);
		return "redirect:/product";
	}
	
}
