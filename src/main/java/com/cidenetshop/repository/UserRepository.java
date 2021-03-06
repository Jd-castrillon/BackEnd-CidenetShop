package com.cidenetshop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cidenetshop.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);


	boolean existsByEmail(String email);

}
