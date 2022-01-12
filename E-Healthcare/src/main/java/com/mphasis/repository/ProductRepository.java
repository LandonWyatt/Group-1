package com.mphasis.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.mphasis.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
