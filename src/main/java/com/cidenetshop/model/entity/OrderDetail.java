package com.cidenetshop.model.entity;

import com.cidenetshop.model.embeddable.OrderDetailKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

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
	@MapsId("idOrder")
	@JoinColumn(name = "id_order")
	private Order order;

	@ManyToOne
	@MapsId("idSize")
	@JoinColumn(name = "id_size")
	private Size size;

	@Column
	private Integer quantity;

	@Column
	private Double salePrice;

	public OrderDetail() {
		super();
		id = new OrderDetailKey();
	}

	

	public OrderDetail(Product product, Order order, Size size, Integer quantity, Double salePrice) {
		super();
		id = new OrderDetailKey();
		this.product = product;
		this.order = order;
		this.size = size;
		this.quantity = quantity;
		this.salePrice = salePrice;
	}



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

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
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

}
