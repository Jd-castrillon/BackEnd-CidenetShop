package com.cidenetshop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cidenetshop.model.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	Optional<Role> findByRole(String role);
	
}
