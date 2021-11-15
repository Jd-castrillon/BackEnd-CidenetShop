package com.cidenetshop.service.api;

import org.springframework.stereotype.Service;

import dto.NewUserDTO;

@Service
public interface UserServiceAPI {

	boolean existByEmail(String email);

	void save(NewUserDTO newUserDTO) throws Exception;

}
