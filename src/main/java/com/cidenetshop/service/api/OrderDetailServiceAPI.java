package com.cidenetshop.service.api;

import java.util.List;

import com.cidenetshop.model.dto.GetOrderDetailDTO;
import com.cidenetshop.model.entity.OrderDetail;

public interface OrderDetailServiceAPI {
	
	
	void saveAll(Iterable<OrderDetail> newOrderDetail);
	
	List<GetOrderDetailDTO> OutOfStock(List<GetOrderDetailDTO> productType) throws Exception;
}
