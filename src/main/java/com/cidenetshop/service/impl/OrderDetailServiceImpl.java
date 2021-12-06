package com.cidenetshop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.Order;
import com.cidenetshop.model.entity.OrderDetail;
import com.cidenetshop.model.embeddable.OrderDetailKey;
import com.cidenetshop.repository.OrderDetailRepository;
import com.cidenetshop.repository.OrderRepository;
import com.cidenetshop.service.api.OrderDetailServiceAPI;
import com.cidenetshop.service.api.ProductServiceAPI;

import com.cidenetshop.model.dto.GetOrderDetailDTO;

@Service
public class OrderDetailServiceImpl implements OrderDetailServiceAPI {
	
	
	private final OrderDetailRepository orderDetailRepository;
	private final ProductServiceAPI productServiceAPI;
	private final OrderRepository orderRepository;

	@Autowired
	public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ProductServiceAPI productServiceAPI,  OrderRepository orderRepository) {
		super();
		this.orderDetailRepository = orderDetailRepository;
		this.productServiceAPI = productServiceAPI;
		this.orderRepository = orderRepository;
		
	}
	
	@Override
	public void save(GetOrderDetailDTO newOrderDetail) {
		
		Optional<Order> order = orderRepository.findById(newOrderDetail.getIdOrder());
		
		if (order.isPresent()) {
			
			OrderDetail orderDetail = new OrderDetail( new OrderDetailKey(newOrderDetail.getIdProduct(), newOrderDetail.getIdProduct()), productServiceAPI.findById(newOrderDetail.getIdProduct()), order.get(),
					newOrderDetail.getSize(), newOrderDetail.getQuantity(), newOrderDetail.getSalePrice());
			orderDetailRepository.save(orderDetail);
		}
	}
	
	
	public void saveAll(Iterable<OrderDetail> newOrderDetail) {
		
		orderDetailRepository.saveAll(newOrderDetail);
		
	}


	
}
