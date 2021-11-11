package com.cidenetshop.service.api;

import com.cidenetshop.model.Order;

import dto.GetOrderDTO;

public interface OrderServiceAPI {
	
	Order saveOrder( Order order) throws Exception;
	
	GetOrderDTO findOrderById(Long orderId) throws Exception;
	
}
