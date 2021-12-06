package com.cidenetshop.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.cidenetshop.model.embeddable.ExistingQuantityKey;

//Tabla encargada de la cantidad de productos existentes por talla
@Entity
@Table(name = "existing_quantity")
public class ExistingQuantity {

	@EmbeddedId
	private ExistingQuantityKey id;

	@ManyToOne
	@MapsId("idProduct")
	@JoinColumn(name = "id_product")
	private Product product;

	@ManyToOne
	@MapsId("idSize")
	@JoinColumn(name = "id_size")
	
	private Size size;

	private Integer existingQuantity;

	public ExistingQuantityKey getId() {
		return id;
	}

	public void setId(ExistingQuantityKey id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Integer getExistingQuantity() {
		return existingQuantity;
	}

	public void setExistingQuantity(Integer existingQuantity) {
		this.existingQuantity = existingQuantity;
	}

	public ExistingQuantity(ExistingQuantityKey id, Product product, Size size, Integer existingQuantity) {
		super();
		this.id = id;
		this.product = product;
		this.size = size;
		this.existingQuantity = existingQuantity;
	}

	public ExistingQuantity() {
		super();
	}

}
