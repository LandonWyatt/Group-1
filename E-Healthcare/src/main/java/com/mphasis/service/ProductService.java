package com.mphasis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		
		productRepository.findAll().forEach(products::add);
		
		return products;
	}
	
	public Optional<Product> getProduct(Long id) {
		return productRepository.findById(id);
	}
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public void updateProduct(Long id, Product product) {
		if(productRepository.findById(id).get() != null) {
			productRepository.save(product);
		}
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
