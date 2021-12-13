package com.cidenetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.OrderDetail;
import com.cidenetshop.repository.OrderDetailRepository;
import com.cidenetshop.service.api.OrderDetailServiceAPI;

@Service
public class OrderDetailServiceImpl implements OrderDetailServiceAPI {

	private final OrderDetailRepository orderDetailRepository;

	@Autowired
	public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
		super();
		this.orderDetailRepository = orderDetailRepository;

	}

	public void saveAll(Iterable<OrderDetail> newOrderDetail) {

		orderDetailRepository.saveAll(newOrderDetail);

	}

}
