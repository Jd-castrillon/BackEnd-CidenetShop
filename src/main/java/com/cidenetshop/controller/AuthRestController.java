package com.cidenetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.configuration.security.jwt.JwtProvider;
import com.cidenetshop.model.dto.JwtDTO;
import com.cidenetshop.model.dto.LoginUserDTO;
import com.cidenetshop.model.dto.MessageDTO;
import com.cidenetshop.model.dto.NewUserDTO;
import com.cidenetshop.service.api.UserServiceAPI;

@RestController
@RequestMapping(value = "/auth")
public class AuthRestController {

	@Autowired
	private UserServiceAPI userServiceAPI;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping("/newuser")
	public ResponseEntity<?> newUser(@RequestBody NewUserDTO newUserDto) {

		try {
			userServiceAPI.save(newUserDto);
			return new ResponseEntity<MessageDTO>(new MessageDTO("user save"), HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginUserDTO loginUserDTO) {

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUserDTO.getUserName(), loginUserDTO.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = jwtProvider.generateToken(authentication);

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

			return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO("Don't found user"),
					HttpStatus.BAD_REQUEST);
		}

	}
}
