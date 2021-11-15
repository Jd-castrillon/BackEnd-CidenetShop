package com.cidenetshop.service.api;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cidenetshop.model.Role;

@Service
public interface RoleServiceAPI {
	
	Optional<Role> findById(Integer id);
	
	Optional<Role> findByRole(String role);
}
