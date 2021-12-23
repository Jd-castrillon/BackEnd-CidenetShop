package com.cidenetshop.service.impl;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.dto.GetOrderDTO;
import com.cidenetshop.model.dto.GetOrderDetailDTO;
import com.cidenetshop.model.dto.NewOrderDTO;
import com.cidenetshop.model.entity.ExistingQuantity;
import com.cidenetshop.model.entity.Order;
import com.cidenetshop.model.entity.OrderDetail;
import com.cidenetshop.model.entity.Product;
import com.cidenetshop.model.entity.User;
import com.cidenetshop.repository.OrderRepository;
import com.cidenetshop.service.api.ExistingQuantityServiceAPI;
import com.cidenetshop.service.api.OrderServiceAPI;
import com.cidenetshop.service.api.ProductServiceAPI;

@Service
public class OrderServiceImpl implements OrderServiceAPI {

	private final ExistingQuantityServiceAPI existingQuantityServiceAPI;

	private final OrderRepository orderRepository;

	private final ProductServiceAPI productServiceAPI;

	private final ModelMapper modelMapper;

	@Autowired
	public OrderServiceImpl(ExistingQuantityServiceAPI existingQuantityServiceAPI, OrderRepository orderRepository,
			ProductServiceAPI productServiceAPI, ModelMapper modelMapper) {
		super();
		this.existingQuantityServiceAPI = existingQuantityServiceAPI;
		this.orderRepository = orderRepository;
		this.productServiceAPI = productServiceAPI;
		this.modelMapper = modelMapper;
	}

	@Transactional
	@Override
	public void saveOrder(Long idUser, NewOrderDTO newOrder) throws Exception {

		final User user = new User();
		user.setIdUser(idUser);

		final Order order = new Order();
		order.setOrderAddress(newOrder.getOrderAddress());
		order.setOrderDate(LocalDate.now(Clock.system(ZoneId.of("America/Bogota"))));
		order.setUser(user);
		order.setOrderDetails(new ArrayList<>());

		for (GetOrderDetailDTO orderDetailDTO : newOrder.getOrderDetails()) {
			final Product product = productServiceAPI.findById(orderDetailDTO.getIdProduct());

			final OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProduct(product);
			orderDetail.setOrder(order);
			orderDetail.setSize(orderDetailDTO.getSize());
			orderDetail.setQuantity(orderDetailDTO.getQuantity());
			orderDetail.setSalePrice(product.getPrice());

			order.getOrderDetails().add(orderDetail);
		}

		orderRepository.save(order);

		for (OrderDetail orderDetail : order.getOrderDetails()) {

			ExistingQuantity stock = existingQuantityServiceAPI
					.findByProductIdAndShortText(orderDetail.getProduct().getId(), orderDetail.getSize());

			stock.setExistingQuantity(stock.getExistingQuantity() - orderDetail.getQuantity());

		}

		// find the existing quantity through a repo/service
		// set new quantity
	}

	public Order findById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isEmpty()) {
			return null;
		}
		return order.get();
	}

	;

//	public void saveOrder(NewOrderDTO newOrder) throws Exception {
//
//		List<OrderDetail> orderDetails = new ArrayList<>() ;
//
//		for (GetOrderDetailDTO i : newOrder.getOrderDetails()) {
//			OrderDetail listOrderD = new OrderDetail(productServiceAPI.findById(i.getIdProduct()), findById(i
//			.getIdOrder()),
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

		GetOrderDTO getOrderDTO = modelMapper.map(orderFound, GetOrderDTO.class);

		return getOrderDTO;
	}

}
