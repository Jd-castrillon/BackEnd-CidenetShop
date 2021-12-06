package com.cidenetshop.model.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class NewUserDTO {
	
	
	@NotBlank
	private String documentType;

	
	@NotBlank
	private String documentNumber;
	
	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;

	
	@NotBlank
	@Min(value = 5, message = "La contraseña debe tener mas de 5 caracteres")
	private String password;

	
	
	private Set<String> roles = new HashSet<>();

	public String getName() {
		return name;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
