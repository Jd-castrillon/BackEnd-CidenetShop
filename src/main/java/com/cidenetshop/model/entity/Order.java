package com.cidenetshop.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	private static final String ID_SEQUENCE_GENERATOR_NAME = "orders_id_sequence_generator";
	private static final String ID_SEQUENCE_NAME = "orders_id_sequence";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE_GENERATOR_NAME)
	@SequenceGenerator(name = ID_SEQUENCE_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "order_address")
	private String orderAddress;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;

	@OneToMany(mappedBy = "order", cascade = { CascadeType.ALL })
	private List<OrderDetail> orderDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Order(Long id, String orderAddress, LocalDate orderDate, User user, List<OrderDetail> orderDetails) {
		super();
		this.id = id;
		this.orderAddress = orderAddress;
		this.orderDate = orderDate;
		this.user = user;
		this.orderDetails = orderDetails;
	}

	public Order(String orderAddress, LocalDate orderDate, User user, List<OrderDetail> orderDetails) {
		super();
		this.orderAddress = orderAddress;
		this.orderDate = orderDate;
		this.user = user;
		this.orderDetails = orderDetails;
	}

	public Order() {
		super();
	}

}
