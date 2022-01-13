package com.mphasis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.model.User;
import com.mphasis.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
}
