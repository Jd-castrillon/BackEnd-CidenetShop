package com.cidenetshop.service.api;

import com.cidenetshop.model.entity.OrderDetail;

public interface OrderDetailServiceAPI {
	
	
	void saveAll(Iterable<OrderDetail> newOrderDetail);
}
