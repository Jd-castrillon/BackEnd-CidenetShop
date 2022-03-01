package com.cidenetshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cidenetshop.model.entity.Product;

public interface ProductRepository extends CrudRepository<Product,Long>{
	
	Optional<Product> findByName(String name);
		
	@Query(nativeQuery = true, value = "select * from products  left join genders  on products.id_gender  =  genders.id WHERE genders.gender = :gender")
	Iterable<Product> findAllByGender(@Param("gender") String gender);
	
	
}
