package com.cidenetshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="talla")
public class Talla {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idtalla;
	
	@Column(name="talla")
	String talla;

	public Integer getIdtalla() {
		return idtalla;
	}

	public void setIdtalla(Integer idtalla) {
		this.idtalla = idtalla;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public Talla(Integer idtalla, String talla) {
		super();
		this.idtalla = idtalla;
		this.talla = talla;
	}

	public Talla() {
		super();
	}
	
	
}
