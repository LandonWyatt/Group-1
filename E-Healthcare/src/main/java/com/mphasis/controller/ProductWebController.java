package com.mphasis.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.mphasis.service.ProductService;

@Controller
public class ProductWebController {
	
	@Autowired
	private ProductController productController;
	private int numEntries = 5;
	private String searchStr = "";
	private List<Product> cartList = new ArrayList<>();
	
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
	

	@GetMapping("/cart")
	public ModelAndView getCart(Map<String, Object> model) {
		model.put("products", cartList);
		return new ModelAndView("cart");
	}
	
	@GetMapping("/save_to_cart/{id}")
	public String saveToCart(Map<String, Object> model, @PathVariable("id") Long id) {
		Product p = productController.getProduct(id).get();
		if(!cartList.contains(p)) {
			cartList.add(p);
		}
		else {
			// add one to cart quantity
			System.out.println("add one");
		}
		model.put("products", cartList);
		return "redirect:/cart";
	}
	
	@GetMapping("/remove_from_cart/{id}")
	public String removeFromCart(Map<String, Object> model, @PathVariable("id") Long id) {
		Product p = productController.getProduct(id).get();
		Iterator<Product> iter = cartList.iterator();
		while(iter.hasNext()) {
			Product item = iter.next();
			if(item.getId() == p.getId()) {
				cartList.remove(item);
			}
		}
		model.put("products", cartList);
		return "redirect:/cart";
	}
	
}
