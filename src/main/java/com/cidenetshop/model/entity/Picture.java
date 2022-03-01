package com.cidenetshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class Picture {

	@Id
	@Column(name = "id_product")
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "id_product")
	private Product product;

	@Column(name = "picture")
	private byte[] picture;

	public Picture() {
		super();
	}

	public Picture(Long id, Product product, byte[] picture) {
		super();
		this.id = id;
		this.product = product;
		this.picture = picture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

}
