package com.cidenetshop.service.api;

import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.User;

import com.cidenetshop.model.dto.NewUserDTO;

@Service
public interface UserServiceAPI {

	boolean existByEmail(String email);

	void save(NewUserDTO newUserDTO) throws Exception;
	
	User findByID(Long id);

}
