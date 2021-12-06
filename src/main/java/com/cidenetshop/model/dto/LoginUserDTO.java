package com.cidenetshop.model.dto;

import org.springframework.lang.NonNull;

public class LoginUserDTO {

	@NonNull
	private String userName;

	@NonNull
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
