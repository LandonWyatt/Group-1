package com.mphasis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.model.Product;
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
	
	public List<Product> getAllProductSearch(String str) {
		List<Product> productNames = new ArrayList<>();
		
		Predicate<Product> prodNamePred = (Product prod) -> {
			if(str != "") {
				if((prod.getName()).toUpperCase().contains(str.toUpperCase())
					|| (prod.getBrand()).toUpperCase().contains(str.toUpperCase()))
					return true;
				else
					return false;
			} else {return true;} // if search string is empty, simply return true for each value
		};
		
		this.getAllProducts().stream().filter(prodNamePred).forEach(productNames::add);
		return productNames;
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
