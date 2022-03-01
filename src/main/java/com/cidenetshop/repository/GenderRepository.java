package com.cidenetshop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cidenetshop.model.entity.Gender;


@Repository
public interface GenderRepository extends CrudRepository<Gender, Integer>{
	
	Optional<Gender> findByGender(String gender);
	
}
