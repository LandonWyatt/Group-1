package com.mphasis.controller;

import java.util.ArrayList;
import java.util.Iterator;
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
public class CartWebController {
	
	@Autowired
	private ProductController productController;
	private List<Product> cartList = new ArrayList<>();

	@GetMapping("/cart")
	public ModelAndView getCart(Map<String, Object> model) {
		model.put("products", cartList);
		return new ModelAndView("cart");
	}
	
	@GetMapping("/save_to_cart/{id}")
	public String saveToCart(Map<String, Object> model, @PathVariable("id") Long id) {
		Product p = productController.getProduct(id);
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
		Product p = productController.getProduct(id);
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
