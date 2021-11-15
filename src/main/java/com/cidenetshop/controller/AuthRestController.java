package com.cidenetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.configuration.security.jwt.JwtProvider;
import com.cidenetshop.service.api.UserServiceAPI;

import dto.JwtDTO;
import dto.LoginUserDTO;
import dto.MessageDTO;
import dto.NewUserDTO;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthRestController {

	@Autowired
	private UserServiceAPI userServiceAPI;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;
	
	
	@PostMapping("/newuser")
	public ResponseEntity<?> newUser(@RequestBody NewUserDTO newUserDto, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity(new MessageDTO("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		try {
			userServiceAPI.save(newUserDto);
			return new ResponseEntity(new MessageDTO("usuario guardado"), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	

	@PostMapping("/login")
	public ResponseEntity<JwtDTO> login(@RequestBody LoginUserDTO loginUserDTO, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return new ResponseEntity(new MessageDTO("Badly placed fields"), HttpStatus.BAD_REQUEST);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUserDTO.getUserName(), loginUserDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

		return new ResponseEntity(jwtDTO, HttpStatus.OK);

	}
}
