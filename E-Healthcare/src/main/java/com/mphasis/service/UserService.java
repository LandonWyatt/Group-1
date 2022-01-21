package com.mphasis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.model.User;
import com.mphasis.repository.UserRepository;

/*
 * Service file interacts with the database through the repository to perform any
 * SQL calls needed for the controllers
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/*
	 * Finds user based on ID
	 */
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}
	
	public Boolean checkUser(Long id) {
		if (userRepository.findById(id) != null)
			return true;
		else
			return false;
	}
	
	public String validateUser(User user) {
		List<User> userlist = userRepository.findByEmail(user.getEmail());
		User userFromDb = userlist.get(0);
		
		if(userFromDb.getPassword().equals(user.getPassword())) {
			return userFromDb.getFirstName();
		}
		
		return null;
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public boolean checkIfUserExists(String email) {
		List<User> userlist = userRepository.findByEmail(email);
		
		if(userlist!=null && !userlist.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
}
