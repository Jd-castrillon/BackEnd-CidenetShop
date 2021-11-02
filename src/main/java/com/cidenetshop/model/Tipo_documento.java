package com.cidenetshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_documento")
public class Tipo_documento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idtipo_documento;
	
	@Column(name ="tipo_documento")
	private String tipoDocumento;

	public Integer getIdtipo_documento() {
		return idtipo_documento;
	}

	public void setIdtipo_documento(Integer idtipo_documento) {
		this.idtipo_documento = idtipo_documento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Tipo_documento(Integer idtipo_documento, String tipoDocumento) {
		super();
		this.idtipo_documento = idtipo_documento;
		this.tipoDocumento = tipoDocumento;
	}

	public Tipo_documento() {
		super();
	}
	
	

}
