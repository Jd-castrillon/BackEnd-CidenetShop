package com.cidenetshop.model.dto;

import java.time.LocalDate;
import java.util.List;

public class GetOrderDTO {

	private Long id;

	private String orderAddress;

	private String userName;

	private LocalDate orderDate;

	private List<GetOrderDetailDTO> OrderDetails;

	public GetOrderDTO(Long id, String orderAddress, String userName, LocalDate orderDate,
			List<GetOrderDetailDTO> orderDetails) {
		super();
		this.id = id;
		this.orderAddress = orderAddress;
		this.userName = userName;
		this.orderDate = orderDate;
		OrderDetails = orderDetails;
	}

	public GetOrderDTO() {
		super();
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public List<GetOrderDetailDTO> getOrderDetails() {
		return OrderDetails;
	}

	public void setOrderDetails(List<GetOrderDetailDTO> orderDetails) {
		OrderDetails = orderDetails;
	}

}
