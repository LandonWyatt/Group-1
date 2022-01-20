package com.mphasis.controller;

import java.util.ArrayList;
import java.util.Collections;
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

@Controller
public class CartWebController {
	
	@Autowired
	private ProductController productController;
	private List<Product> cartList = Collections.synchronizedList(new ArrayList<>());
	private String qtyInfo = "";
	private double select = 10;
	
	@GetMapping("/cart")
	public ModelAndView getCart(Map<String, Object> model) {
		model.put("subTotal", select);
		model.put("products", cartList);
		return new ModelAndView("cart");
	}
	
	@GetMapping("/save_to_cart/{id}")
	public String saveToCart(Map<String, Object> model, @PathVariable("id") Long id) {
		Product p = productController.getProduct(id);
		if(cartList.isEmpty()) {
			cartList.add(p);
		}
		else {
			boolean inCart = false;
			Iterator<Product> iter = cartList.iterator();
			while(iter.hasNext()) {
				Product item = iter.next();
				if(item.getId() == p.getId()) {
					inCart = true;
				}
			}
			if(inCart) {
				System.out.println("ADD ONE");
			}
			else {
				cartList.add(p);
			}
			System.out.println(cartList.toString());
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
				iter.remove();
			}
		}
		model.put("products", cartList);
		return "redirect:/cart";
	}
	
	@PostMapping("/update_cart")
	public ModelAndView updateCart(Map<String, Object> model, @RequestBody Map<String, String> data){
		qtyInfo = data.get("qtyInfo");
		Long id = Long.parseLong(data.get("id"));
		Product p = productController.getProduct(id);
		double subTotal = p.getPrice() * Double.parseDouble(qtyInfo);
		model.put("subTotal", subTotal);
		select = subTotal;
		return new ModelAndView("redirect:/cart");
	}
	
}
