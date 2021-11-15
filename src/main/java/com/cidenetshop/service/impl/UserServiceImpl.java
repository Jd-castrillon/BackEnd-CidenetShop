package com.cidenetshop.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.Role;
import com.cidenetshop.model.User;
import com.cidenetshop.repository.RoleRepository;
import com.cidenetshop.repository.UserRepository;
import com.cidenetshop.service.api.RoleServiceAPI;
import com.cidenetshop.service.api.UserServiceAPI;

import dto.NewUserDTO;

@Service
public class UserServiceImpl implements UserServiceAPI {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	
	

	@Autowired
	private RoleServiceAPI roleServiceAPI;

	@Override
	public boolean existByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void save(NewUserDTO newUserDTO) throws Exception {

		try {

			if (userRepository.existsByEmail(newUserDTO.getEmail()))
				throw new Exception("El correo ya existe");

			User user = new User(newUserDTO.getDocumentNumber(), newUserDTO.getName(), newUserDTO.getEmail(),
					newUserDTO.getDocumentType(), passwordEncoder.encode(newUserDTO.getPassword()));

			Set<Role> roles = new HashSet<>();

			roles.add(roleServiceAPI.findByRole("cliente").get());
			
//			if(newUserDTO.getRoles().contains("admin"))
//				roles.add(roleServiceAPI.findByRole("admin").get());

			user.setRoles(roles);

			userRepository.save(user);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ha ocurrido un error inesperado al guardar el producto en la base de datos.");
		}

	}

}
