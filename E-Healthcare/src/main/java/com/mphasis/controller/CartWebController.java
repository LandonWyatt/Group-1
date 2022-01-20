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
import org.springframework.web.servlet.ModelAndView;

import com.mphasis.model.Product;

@Controller
public class CartWebController {
	
	@Autowired
	private ProductController productController;
	private Map<Product, Integer> cartMap = new HashMap<Product, Integer>();
	private List<Product> cartKeyList = Collections.synchronizedList(new ArrayList<>());
	private double totalSum = 0;
	
	@GetMapping("/cart")
	public ModelAndView getCart(Map<String, Object> model) {
		model.put("totalSum", totalSum);
		model.put("products", cartMap);
		return new ModelAndView("cart");
	}
	
	@GetMapping("/save_to_cart/{id}")
	public String saveToCart(Map<String, Object> model, @PathVariable("id") Long id) {
		Product p = productController.getProduct(id);
		if(cartKeyList.isEmpty()) {
			cartMap.put(p, 1);
			cartKeyList = new ArrayList<>(cartMap.keySet());
		}
		else {
			boolean inCart = false;
			Iterator<Product> iter = cartKeyList.iterator();
			while(iter.hasNext()) {
				Product item = iter.next();
				if(item.getId() == id) {
					cartMap.put(item, cartMap.get(item) + 1);
					inCart = true;
				}
			}
			if(!inCart) {
				cartMap.put(p, 1);
				cartKeyList = new ArrayList<>(cartMap.keySet());
			}
		}
		return "redirect:/totalSum";
	}
	
	@GetMapping("/remove_from_cart/{id}")
	public String removeFromCart(Map<String, Object> model, @PathVariable("id") Long id) {
		System.out.println(cartKeyList);
		Iterator<Product> iter = cartKeyList.iterator();
		while(iter.hasNext()) {
			Product item = iter.next();
			if(item.getId() == id) {
				cartMap.remove(item);
				cartKeyList = new ArrayList<>(cartMap.keySet());
				break;
			}
		}
		return "redirect:/totalSum";
	}
	
	@GetMapping("/totalSum")
	public String calculateTotalSum(Map<String, Object> model) {
		totalSum = 0;
		
		for(Map.Entry<Product, Integer> prodEntry : cartMap.entrySet()) {
			totalSum += prodEntry.getKey().getPrice() * prodEntry.getValue();
		}
		
		System.out.println(totalSum);
		
		return "redirect:/cart";
	}
	
	@GetMapping("/checkoutReceipt")
	public String checkoutReceipt(Map<String, Object> model) {
		model.put("totalSum", totalSum);
		model.put("products", cartMap);
		return "thanks";
	}
	
//	@PostMapping("/update_cart")
//	public ModelAndView updateCart(Map<String, Object> model, @RequestBody Map<String, String> data){
//		qtyInfo = data.get("qtyInfo");
//		Long id = Long.parseLong(data.get("id"));
//		Product p = productController.getProduct(id);
//		double subTotal = p.getPrice() * Double.parseDouble(qtyInfo);
//		model.put("subTotal", subTotal);
//		select = subTotal;
//		return new ModelAndView("redirect:/cart");
//	}
	
}
