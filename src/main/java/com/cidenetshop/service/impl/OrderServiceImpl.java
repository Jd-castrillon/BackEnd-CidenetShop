package com.cidenetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.Order;
import com.cidenetshop.model.OrderDetail;
import com.cidenetshop.repository.OrderRepository;
import com.cidenetshop.service.api.OrderDetailServiceAPI;
import com.cidenetshop.service.api.OrderServiceAPI;
import com.cidenetshop.service.api.ProductServiceAPI;
import com.cidenetshop.service.api.UserServiceAPI;

import dto.GetOrderDTO;
import dto.GetOrderDetailDTO;
import dto.NewOrderDTO;

@Service
public class OrderServiceImpl implements OrderServiceAPI {

	private final UserServiceAPI userServiceAPI;

	private final OrderRepository orderRepository;

	private final ProductServiceAPI productServiceAPI;

	private final OrderDetailServiceAPI orderDetailServiceAPI;

	

	public OrderServiceImpl(UserServiceAPI userServiceAPI, OrderRepository orderRepository,
			ProductServiceAPI productServiceAPI, OrderDetailServiceAPI orderDetailServiceAPI) {
		super();
		this.userServiceAPI = userServiceAPI;
		this.orderRepository = orderRepository;
		this.productServiceAPI = productServiceAPI;
		this.orderDetailServiceAPI = orderDetailServiceAPI;
	}




	public Order findById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isEmpty()) {
			return null;
		}
		return order.get();
	};

	
	
	
//	public void saveOrder(NewOrderDTO newOrder) throws Exception {
//
//		List<OrderDetail> orderDetails = new ArrayList<>() ;
//
//		for (GetOrderDetailDTO i : newOrder.getOrderDetails()) {
//			OrderDetail listOrderD = new OrderDetail(productServiceAPI.findById(i.getIdProduct()), findById(i.getIdOrder()),
//					i.getSize(), i.getQuantity(), i.getSalePrice());
//			
//			orderDetails.add(listOrderD);
//			
//		}
//
//		Order order2 = new Order(newOrder.getOrderAddress(), newOrder.getOrderDate(),
//				userServiceAPI.findByID(newOrder.getIdUser()), orderDetails);
//		
//		orderRepository.save(order2);
//		
//		for(GetOrderDetailDTO i : newOrder.getOrderDetails()) {
//			orderDetailServiceAPI.save(i);
//		}
//	}
//	

	
	
	@Override
	public GetOrderDTO findOrderById(Long orderId) throws Exception {

		final Optional<Order> repoResponse = this.orderRepository.findById(orderId);

		if (repoResponse.isEmpty()) {
			new Exception("Orden no encontrada para el id " + orderId);
		}

		final Order orderFound = repoResponse.get();

		ModelMapper modelMapper = new ModelMapper();

		GetOrderDTO getOrderDTO = modelMapper.map(orderFound, GetOrderDTO.class);

		return getOrderDTO;
	}




@Override
public void saveOrder(NewOrderDTO newOrder) throws Exception {
	
	
}

}
