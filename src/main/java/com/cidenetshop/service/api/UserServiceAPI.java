package com.cidenetshop.service.api;

import org.springframework.stereotype.Service;

import com.cidenetshop.model.User;

import dto.NewUserDTO;

@Service
public interface UserServiceAPI {

	boolean existByEmail(String email);

	void save(NewUserDTO newUserDTO) throws Exception;
	
	User findByID(Long id);

}
