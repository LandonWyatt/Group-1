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

@Controller
public class CartWebController {
	
	@Autowired
	private ProductController productController;
	private Map<Product, Double> cartMap = new HashMap<Product, Double>();
	private List<Product> cartKeyList = Collections.synchronizedList(new ArrayList<>());
	private List<Double> cartQtyList = Collections.synchronizedList(new ArrayList<>());
	private String qtyInfo = "";
	private double select = 10;
	
	@GetMapping("/cart")
	public ModelAndView getCart(Map<String, Object> model) {
		model.put("subTotal", select);
		model.put("products", cartMap);
//		model.put("products", cartKeyList);
//		model.put("quantities", cartQtyList);
		return new ModelAndView("cart");
	}
	
	@GetMapping("/save_to_cart/{id}")
	public String saveToCart(Map<String, Object> model, @PathVariable("id") Long id) {
		Product p = productController.getProduct(id);
		if(cartKeyList.isEmpty()) {
			cartMap.put(p, 3.0);
			cartKeyList = new ArrayList<>(cartMap.keySet());
			cartQtyList = new ArrayList<>(cartMap.values());
		}
		else {
			boolean inCart = false;
			Iterator<Product> iter = cartKeyList.iterator();
			while(iter.hasNext()) {
				Product item = iter.next();
				if(item.getId() == id) {
					inCart = true;
				}
			}
			if(inCart) {
				System.out.println("ADD ONE");
			}
			else {
				cartMap.put(p,1.0);
				cartKeyList = new ArrayList<>(cartMap.keySet());
				cartQtyList = new ArrayList<>(cartMap.values());
			}
		}
//		model.put("products", cartKeyList);
		return "redirect:/cart";
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
				cartQtyList = new ArrayList<>(cartMap.values());
				break;
			}
		}
//		model.put("products", cartKeyList);
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
