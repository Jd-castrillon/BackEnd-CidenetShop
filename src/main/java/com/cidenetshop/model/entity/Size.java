package com.cidenetshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sizes")
public class Size {

	private static final String ID_SEQUENCE_GENERATOR_NAME = "sizes_id_sequence_generator";
	private static final String ID_SEQUENCE_NAME = "sizes_id_sequence";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE_GENERATOR_NAME)
	@SequenceGenerator(name = ID_SEQUENCE_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "shortText")
	private String shortText;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortText() {
		return shortText;
	}

	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	public Size(Integer id, String shortText) {
		super();
		this.id = id;
		this.shortText = shortText;

	}

	public Size() {
		super();
	}

}
