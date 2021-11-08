package com.cidenetshop.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.cidenetshop.model.embeddable.OrderDetailKey;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	@EmbeddedId
	private OrderDetailKey id;

	@ManyToOne
	@MapsId("idProduct")
	@JoinColumn(name = "id_product")
	private Product product;

	@ManyToOne
	@MapsId("IdOrder")
	@JoinColumn(name = "id_order")
	private Order order;

	private Integer quantity;

	private Double salePrice;

	public OrderDetailKey getId() {
		return id;
	}

	public void setId(OrderDetailKey id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public OrderDetail(OrderDetailKey id, Product product, Order order, Integer quantity, Double salePrice) {
		super();
		this.id = id;
		this.product = product;
		this.order = order;
		this.quantity = quantity;
		this.salePrice = salePrice;
	}

	public OrderDetail() {
		super();
	}

}
