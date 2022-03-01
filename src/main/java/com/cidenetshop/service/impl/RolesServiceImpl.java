package com.cidenetshop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.Role;
import com.cidenetshop.repository.RoleRepository;
import com.cidenetshop.service.api.RoleServiceAPI;

@Service
public class RolesServiceImpl implements RoleServiceAPI {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Optional<Role> findById(Integer id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id);
	}

	@Override
	public Optional<Role> findByRole(String role) {
		// TODO Auto-generated method stub
		return roleRepository.findByRole(role);
	}

}
