package com.cidenetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cidenetshop.configuration.security.UserDetail;
import com.cidenetshop.model.User;
import com.cidenetshop.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	};

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).get();
		return UserDetail.build(user);


	}
	
}
