package com.cidenetshop.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.DocumentType;
import com.cidenetshop.model.Role;
import com.cidenetshop.model.User;
import com.cidenetshop.repository.DocumentTypeRepository;
import com.cidenetshop.repository.UserRepository;
import com.cidenetshop.service.api.RoleServiceAPI;
import com.cidenetshop.service.api.UserServiceAPI;

import dto.NewUserDTO;

@Service
public class UserServiceImpl implements UserServiceAPI {

	private PasswordEncoder passwordEncoder;

	private UserRepository userRepository;

	private DocumentTypeRepository documentTypeRepository;

	private RoleServiceAPI roleServiceAPI;

	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
			DocumentTypeRepository documentTypeRepository, RoleServiceAPI roleServiceAPI) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.documentTypeRepository = documentTypeRepository;
		this.roleServiceAPI = roleServiceAPI;
	}

	@Override
	public boolean existByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	
	 public User findByID(Long id) {
		
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			return null;
		}
		return user.get();
		
	}

	@Override
	public void save(NewUserDTO newUserDTO) throws Exception {

		try {

			if (userRepository.existsByEmail(newUserDTO.getEmail()))
				throw new Exception("El correo ya existe");
			
			DocumentType documenType = documentTypeRepository.findByDocumentType(newUserDTO.getDocumentType()).get();
			
			User user = new User(newUserDTO.getDocumentNumber(), newUserDTO.getName(), newUserDTO.getEmail(),
					documenType, passwordEncoder.encode(newUserDTO.getPassword()));

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
