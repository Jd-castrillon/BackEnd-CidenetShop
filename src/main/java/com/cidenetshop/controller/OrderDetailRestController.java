package com.cidenetshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.model.dto.GetOrderDetailDTO;
import com.cidenetshop.model.dto.MessageDTO;
import com.cidenetshop.service.api.OrderDetailServiceAPI;

@RestController
@RequestMapping(value = "/orderdetails")
public class OrderDetailRestController {

	private final OrderDetailServiceAPI orderDetailServiceAPI;

	@Autowired
	public OrderDetailRestController(OrderDetailServiceAPI orderDetailServiceAPI) {
		super();
		this.orderDetailServiceAPI = orderDetailServiceAPI;
	}

	@PostMapping(value = "/outofstock")
	public ResponseEntity<Object> productsOutOfStock(@RequestBody List<GetOrderDetailDTO> listOrderDetails) {

		try {
			List<GetOrderDetailDTO> productsDTOOutOfStock = orderDetailServiceAPI.OutOfStock(listOrderDetails);
			return new ResponseEntity<Object>(productsDTOOutOfStock, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new MessageDTO(e.getMessage()), HttpStatus.OK);
		}

	}

}
