package com.cidenetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.model.dto.GetUserDTO;
import com.cidenetshop.model.dto.MessageDTO;
import com.cidenetshop.service.api.UserServiceAPI;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

	private final UserServiceAPI userServiceAPI;

	@Autowired
	public UserRestController(UserServiceAPI userServiceAPI) {
		super();
		this.userServiceAPI = userServiceAPI;
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {

		try {
			final GetUserDTO userDTO = userServiceAPI.findByEmail(email);
			return new ResponseEntity<GetUserDTO>(userDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

}
