package com.cidenetshop.model.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class NewOrderDTO {
	
	@NotBlank
	private String orderAddress;
	
	@NotBlank
	private List<GetOrderDetailDTO> orderDetails;

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public List<GetOrderDetailDTO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<GetOrderDetailDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
