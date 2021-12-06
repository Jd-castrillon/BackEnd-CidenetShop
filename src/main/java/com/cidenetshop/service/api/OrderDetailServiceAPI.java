package com.cidenetshop.service.api;

import com.cidenetshop.model.OrderDetail;

import dto.GetOrderDetailDTO;

public interface OrderDetailServiceAPI {
	void save (GetOrderDetailDTO newOrderDetail); 
	
	void saveAll(Iterable<OrderDetail> newOrderDetail);
}
