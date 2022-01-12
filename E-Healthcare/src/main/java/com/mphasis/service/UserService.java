package com.mphasis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.model.User;
import com.mphasis.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllProducts() {
		List<User> products = new ArrayList<>();
		
		userRepository.findAll().forEach(products::add);
		
		return products;
	}
	
	public Optional<User> getProduct(Long id) {
		return userRepository.findById(id);
	}
	
	public void addProduct(User user) {
		userRepository.save(user);
	}
	
	public void updateProduct(Long id, User user) {
		if(userRepository.findById(id).get() != null) {
			userRepository.save(user);
		}
	}
	
	public void deleteProduct(Long id) {
		userRepository.deleteById(id);
	}
}
