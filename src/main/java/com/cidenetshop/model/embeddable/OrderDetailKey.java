package com.cidenetshop.model.embeddable;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailKey implements Serializable {

	@Column(name = "id_order")
	private Long idOrder;

	@Column(name = "id_product")
	private Long idProduct;

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public OrderDetailKey(Long idOrder, Long idProduct) {
		super();
		this.idOrder = idOrder;
		this.idProduct = idProduct;
	}

	public OrderDetailKey() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOrder, idProduct);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailKey other = (OrderDetailKey) obj;
		return Objects.equals(idOrder, other.idOrder) && Objects.equals(idProduct, other.idProduct);
	}

}
