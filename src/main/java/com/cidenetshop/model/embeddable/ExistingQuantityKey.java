package com.cidenetshop.model.embeddable;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExistingQuantityKey implements Serializable {

	@Column(name = "id_product")
	private Long idProduct;

	@Column(name = "id_size")
	private Integer idSize;

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getIdSize() {
		return idSize;
	}

	public void setIdSize(Integer idSize) {
		this.idSize = idSize;
	}

	public ExistingQuantityKey(Long idProduct, Integer idSize) {
		super();
		this.idProduct = idProduct;
		this.idSize = idSize;
	}

	public ExistingQuantityKey() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProduct, idSize);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExistingQuantityKey other = (ExistingQuantityKey) obj;
		return Objects.equals(idProduct, other.idProduct) && Objects.equals(idSize, other.idSize);
	}

}