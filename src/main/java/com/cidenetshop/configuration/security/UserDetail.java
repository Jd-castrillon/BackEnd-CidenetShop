package com.cidenetshop.configuration.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cidenetshop.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

public class UserDetail implements UserDetails {

	private String name;

	private String email;

	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public static UserDetail build(User user) {
		List<SimpleGrantedAuthority> authorities =
				user.getRoles().stream().
				map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());

		return new UserDetail(user.getName(),user.getEmail(),user.getPassword(),authorities);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getName() {
		return name;
	}

	public UserDetail(String name, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}





}
