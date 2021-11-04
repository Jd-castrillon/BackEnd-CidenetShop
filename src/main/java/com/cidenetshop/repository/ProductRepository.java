package com.cidenetshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.cidenetshop.model.Product;

public interface ProductRepository extends CrudRepository<Product,Long>{

}
