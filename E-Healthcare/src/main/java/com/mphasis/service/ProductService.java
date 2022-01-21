package com.mphasis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.model.Product;
import com.mphasis.repository.ProductRepository;

/*
 * Service file interacts with the database through the repository to perform any
 * SQL calls needed for the controllers
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	/*
	 * Retrieves all entries from within DB table
	 */
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		
		productRepository.findAll().forEach(products::add);
		
		return products;
	}
	
	/*
	 * Retrieves all entries from within DB table that contain
	 * a specific string in the Name and/or Brand
	 */
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
	
	/*
	 * Retrieves a product based on ID
	 */
	public Optional<Product> getProduct(Long id) {
		return productRepository.findById(id);
	}
	
	/*
	 * Adds product to DB table with given product
	 */
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	/*
	 * Updates a product with given ID within DB with changes in given Product
	 */
	public void updateProduct(Long id, Product product) {
		if(productRepository.findById(id).get() != null) {
			productRepository.save(product);
		}
	}
	
	/*
	 * Deletes product from DB based on ID
	 */
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
}
