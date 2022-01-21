package com.mphasis.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.mphasis.model.Product;

/*
 * Web Controller that will redirect all mappings for any cart related method calls
 */
@Controller
public class CartWebController {
	
	@Autowired
	private ProductController productController;
	// Creates cart map that will contain all items from user's cart
	private Map<Product, Integer> cartMap = new HashMap<Product, Integer>();
	// list of products from cartMap to then iterate through
	private List<Product> cartKeyList = Collections.synchronizedList(new ArrayList<>());
	private double totalSum = 0;
	
	/*
	 * Sends initial cart.html page to user with totalSum and their products
	 */
	@GetMapping("/cart")
	public ModelAndView getCart(Map<String, Object> model) {
		model.put("totalSum", totalSum);
		model.put("products", cartMap);
		return new ModelAndView("cart");
	}
	
	/*
	 * Saves product to cart based on the id that is retrieved
	 */
	@GetMapping("/save_to_cart/{id}")
	public String saveToCart(Map<String, Object> model, @PathVariable("id") Long id) {
		Product p = productController.getProduct(id);
		// Check if Map is empty, if so, then place product with quantity 1
		if(cartKeyList.isEmpty()) {
			cartMap.put(p, 1);
			cartKeyList = new ArrayList<>(cartMap.keySet());
		}
		else { // else, see if product is already in cart
			boolean inCart = false;
			Iterator<Product> iter = cartKeyList.iterator();
			while(iter.hasNext()) {
				Product item = iter.next();
				if(item.getId() == id) { // if so, then increase quantity by one
					cartMap.put(item, cartMap.get(item) + 1);
					inCart = true;
				}
			}
			if(!inCart) { // else, if product is not in cart, add it
				cartMap.put(p, 1);
				cartKeyList = new ArrayList<>(cartMap.keySet());
			}
		}
		return "redirect:/totalSum";
	}
	
	/*
	 * Remove product from cart based on ID
	 */
	@GetMapping("/remove_from_cart/{id}")
	public String removeFromCart(Map<String, Object> model, @PathVariable("id") Long id) {
		System.out.println(cartKeyList);
		Iterator<Product> iter = cartKeyList.iterator();
		while(iter.hasNext()) { // Find product, then delete
			Product item = iter.next();
			if(item.getId() == id) {
				cartMap.remove(item);
				cartKeyList = new ArrayList<>(cartMap.keySet());
				break;
			}
		}
		return "redirect:/totalSum";
	}
	
	@PostMapping("/update_cart")
	public String updateQty(Map<String, Object> model, @RequestBody Map<String, String> data) {
		Product prod = new Product();
			
		for (Product product : cartKeyList) {
			if (product.getId() == Long.parseLong(data.get("id")))
				prod = product;
		}
		if(Integer.parseInt(data.get("qtyInfo")) < 1)
			cartMap.put(prod, 1);
		else
			cartMap.put(prod, Integer.parseInt(data.get("qtyInfo")));
		return "redirect:/totalSum";
	}
	
	/*
	 * Calculates total sum in cart by going through each Map entry
	 * and adding the price * quantity to the total sum
	 *  - called each time a new product is added or deleted
	 */
	@GetMapping("/totalSum")
	public String calculateTotalSum(Map<String, Object> model) {
		totalSum = 0;
		
		for(Map.Entry<Product, Integer> prodEntry : cartMap.entrySet()) {
			totalSum += prodEntry.getKey().getPrice() * prodEntry.getValue();
		}
		
		return "redirect:/cart";
	}
	
	/*
	 * Sends information to the checkout receipt page and returns to user
	 */
	@GetMapping("/checkoutReceipt")
	public String checkoutReceipt(Map<String, Object> model) {
		Map<Product, Integer> cartMapHolder = new HashMap<Product, Integer>();
		double totalSumHolder = 0;
		cartMapHolder = cartMap;
		totalSumHolder = totalSum;
		model.put("totalSum", totalSumHolder);
		model.put("products", cartMapHolder);
		cartMap = new HashMap<>();
		cartKeyList = Collections.synchronizedList(new ArrayList<>());
		totalSum = 0;
		return "thanks";
	}
	
}
