package com.mphasis.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

import com.mphasis.entity.Products;


@Service
public class ProductsService {
	
	
	private List<Products> products = Arrays.asList();

	public List<Products> getAllProducts() {
		// TODO Auto-generated method stub
		return products;
	}

	public Products getProduct(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
