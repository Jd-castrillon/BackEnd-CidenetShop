package com.cidenetshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idrol;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "sale_price")
	private Double salePrice;

	@Column(name = "id_product")
	private Long product;

	@Column(name = "id_order")
	private Long order;

	public Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public OrderDetail(Integer idrol, Integer quantity, Double salePrice, Long product, Long order) {
		super();
		this.idrol = idrol;
		this.quantity = quantity;
		this.salePrice = salePrice;
		this.product = product;
		this.order = order;
	}

	public OrderDetail() {
		super();
	}

}
