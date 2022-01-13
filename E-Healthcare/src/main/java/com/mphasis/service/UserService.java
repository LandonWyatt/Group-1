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
	
	public List<User> getAllUsers() {
		List<User> products = new ArrayList<>();
		
		userRepository.findAll().forEach(products::add);
		
		return products;
	}
	
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public void updateUser(Long id, User user) {
		if(userRepository.findById(id).get() != null) {
			userRepository.save(user);
		}
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
