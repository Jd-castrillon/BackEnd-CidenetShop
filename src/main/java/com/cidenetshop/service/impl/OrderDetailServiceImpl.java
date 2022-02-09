package com.cidenetshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.dto.GetOrderDetailDTO;
import com.cidenetshop.model.entity.ExistingQuantity;
import com.cidenetshop.model.entity.OrderDetail;
import com.cidenetshop.model.entity.User;
import com.cidenetshop.repository.OrderDetailRepository;
import com.cidenetshop.service.api.ExistingQuantityServiceAPI;
import com.cidenetshop.service.api.OrderDetailServiceAPI;

@Service
public class OrderDetailServiceImpl implements OrderDetailServiceAPI {

	private final OrderDetailRepository orderDetailRepository;
	
	private final ExistingQuantityServiceAPI existingQuantityServiceAPI;

	@Autowired
	public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ExistingQuantityServiceAPI existingQuantityServiceAPI) {
		super();
		this.orderDetailRepository = orderDetailRepository;
		this.existingQuantityServiceAPI = existingQuantityServiceAPI;

	}

	public void saveAll(Iterable<OrderDetail> newOrderDetail) {

		orderDetailRepository.saveAll(newOrderDetail);

	}
	
	@Override
	public List<GetOrderDetailDTO> OutOfStock(List<GetOrderDetailDTO> listOrderDetails) throws Exception {

		List<GetOrderDetailDTO> productOutOfStock = new ArrayList<>();

		for (GetOrderDetailDTO orderDetail : listOrderDetails) {
			User user = new User();

			user.setIdUser(orderDetail.getIdProduct());

			ExistingQuantity stock = existingQuantityServiceAPI.findByProductIdAndShortText(user.getIdUser(),
					orderDetail.getSize());

			if (orderDetail.getQuantity() > stock.getExistingQuantity()) {

				GetOrderDetailDTO newOrderDetail = new GetOrderDetailDTO();

				newOrderDetail.setIdProduct(orderDetail.getIdProduct());
				newOrderDetail.setSize(orderDetail.getSize());
				newOrderDetail.setQuantity(stock.getExistingQuantity());

				productOutOfStock.add(newOrderDetail);

			}
		}

		if (productOutOfStock.isEmpty())
			throw new Exception("Todos los productos tienen stock suficiente");

		return productOutOfStock;
	};

}
