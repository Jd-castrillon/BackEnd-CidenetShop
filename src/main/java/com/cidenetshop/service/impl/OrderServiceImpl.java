package com.cidenetshop.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.SourceGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.Order;
import com.cidenetshop.model.OrderDetail;
import com.cidenetshop.repository.OrderRepository;
import com.cidenetshop.service.api.OrderServiceAPI;

import dto.GetOrderDTO;

@Service
public class OrderServiceImpl implements OrderServiceAPI {
	
	private static final int Order = 0;
	private final OrderRepository orderRepository;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	

	@Override
	public Order saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetOrderDTO findOrderById(Long orderId) throws Exception {
		
		final Optional<Order> repoResponse = this.orderRepository.findById(orderId);
		
		if(repoResponse.isEmpty()) {
			new Exception("Orden no encontrada para el id " + orderId);
		}
		
		final Order orderFound = repoResponse.get();
		
		ModelMapper modelMapper = new ModelMapper();
		
		GetOrderDTO getOrderDTO = modelMapper.map( orderFound, GetOrderDTO.class);
		
		return getOrderDTO;
	}

}
