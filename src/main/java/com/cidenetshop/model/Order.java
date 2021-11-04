package com.cidenetshop.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "order_address")
	private String orderAddress;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;
	
	@ManyToMany
	@JoinTable(name="order_details",
			joinColumns = @JoinColumn(name="id_order"),
			inverseJoinColumns = @JoinColumn(name="id_product"))
	private List<Product> product;
		

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

	public Order(Long id, String orderAddress, LocalDate orderDate, User user) {
		super();
		this.id = id;
		this.orderAddress = orderAddress;
		this.orderDate = orderDate;
		this.user = user;
	}

	public Order() {
		super();
	}

}
