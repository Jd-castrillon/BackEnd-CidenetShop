package com.cidenetshop.service.api;

import com.cidenetshop.model.entity.OrderDetail;

import com.cidenetshop.model.dto.GetOrderDetailDTO;

public interface OrderDetailServiceAPI {
	
	
	void saveAll(Iterable<OrderDetail> newOrderDetail);
}
