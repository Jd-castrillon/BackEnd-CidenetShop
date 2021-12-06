package com.cidenetshop.service.api;

import com.cidenetshop.model.Order;

import dto.GetOrderDTO;
import dto.NewOrderDTO;

public interface OrderServiceAPI {
	
	void saveOrder( NewOrderDTO newOrder) throws Exception;
	
	Order findById(Long id);
	
	GetOrderDTO findOrderById(Long orderId) throws Exception;
	
}
