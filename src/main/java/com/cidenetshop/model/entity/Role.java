package com.cidenetshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	private static final String ID_SEQUENCE_GENERATOR_NAME = "roles_id_sequence_generator";
    private static final String ID_SEQUENCE_NAME = "roles_id_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE_GENERATOR_NAME)
    @SequenceGenerator(name = ID_SEQUENCE_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "role")
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Role(Integer id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Role() {
		super();
	}

}
