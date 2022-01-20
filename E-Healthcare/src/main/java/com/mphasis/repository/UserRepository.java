package com.mphasis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mphasis.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
		public List<User> findByEmail(String email);
}
