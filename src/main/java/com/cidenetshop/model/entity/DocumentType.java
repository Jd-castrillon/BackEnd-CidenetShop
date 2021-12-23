package com.cidenetshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "document_types")
public class DocumentType {

	private static final String ID_SEQUENCE_GENERATOR_NAME = "document_types_id_sequence_generator";
    private static final String ID_SEQUENCE_NAME = "document_types_id_sequence";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE_GENERATOR_NAME)
    @SequenceGenerator(name = ID_SEQUENCE_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME, allocationSize = 1)
	private Integer id;

	@Column(name = "document_type")
	private String documentType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public DocumentType(Integer id, String documentType) {
		super();
		this.id = id;
		this.documentType = documentType;
	}

	public DocumentType() {
		super();
	}

}
