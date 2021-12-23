package com.cidenetshop.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "users")
public class User {
	private static final String ID_SEQUENCE_GENERATOR_NAME = "users_id_sequence_generator";
    private static final String ID_SEQUENCE_NAME = "users_id_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE_GENERATOR_NAME)
    @SequenceGenerator(name = ID_SEQUENCE_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
	private Long idUser;

	@NonNull
	@Column(name = "document_number")
	private String documentNumber;

	@NonNull
	@Column(name = "name")
	private String name;

	@NonNull
	@Column(name = "email")
	private String email;

	@NonNull
	@Column(name = "password")
	private String password;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_document_type")
	private DocumentType documentType;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<Role> roles;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public User(Long idUser, String documentNumber, String name, String email, String password,
			DocumentType documentType, Set<Role> roles) {
		super();
		this.idUser = idUser;
		this.documentNumber = documentNumber;
		this.name = name;
		this.email = email;
		this.password = password;
		this.documentType = documentType;
		this.roles = roles;
	}



	public User(String documentNumber, String name, String email, DocumentType documentType, String password) {
		super();
		this.documentNumber = documentNumber;
		this.name = name;
		this.email = email;
		this.password = password;
		this.documentType = documentType;
	}

	public User() {
		super();
	}

}
