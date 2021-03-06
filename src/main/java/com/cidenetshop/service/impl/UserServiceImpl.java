package com.cidenetshop.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.dto.GetUserDTO;
import com.cidenetshop.model.dto.NewUserDTO;
import com.cidenetshop.model.entity.DocumentType;
import com.cidenetshop.model.entity.Role;
import com.cidenetshop.model.entity.User;
import com.cidenetshop.repository.DocumentTypeRepository;
import com.cidenetshop.repository.UserRepository;
import com.cidenetshop.service.api.RoleServiceAPI;
import com.cidenetshop.service.api.UserServiceAPI;

@Service
public class UserServiceImpl implements UserServiceAPI {

	private PasswordEncoder passwordEncoder;

	private UserRepository userRepository;

	private DocumentTypeRepository documentTypeRepository;

	private RoleServiceAPI roleServiceAPI;

	private final ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
			DocumentTypeRepository documentTypeRepository, RoleServiceAPI roleServiceAPI, ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
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
				throw new Exception("That email already exists");

			DocumentType documenType = documentTypeRepository.findByDocumentType(newUserDTO.getDocumentType()).get();

			User user = new User(newUserDTO.getDocumentNumber(), newUserDTO.getName(), newUserDTO.getEmail(),
					documenType, passwordEncoder.encode(newUserDTO.getPassword()));

			Set<Role> roles = new HashSet<>();

			roles.add(roleServiceAPI.findByRole("client").get());

			user.setRoles(roles);

			userRepository.save(user);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ha ocurrido un error inesperado al guardar el usuario en la base de datos.");
		}

	}

	@Override
	public GetUserDTO findByEmail(String email) {

		final Optional<User> repoResponse = userRepository.findByEmail(email);

		if (repoResponse.isEmpty()) {
			new Exception("Don't found user by email:" + email);
		}

		final User userFound = repoResponse.get();

		GetUserDTO userDTO = modelMapper.map(userFound, GetUserDTO.class);

		return userDTO;

	}

}
