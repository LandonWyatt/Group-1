package com.mphasis.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.mphasis.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
