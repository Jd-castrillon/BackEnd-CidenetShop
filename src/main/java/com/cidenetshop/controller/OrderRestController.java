package com.cidenetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.configuration.security.UserDetail;
import com.cidenetshop.model.dto.GetOrderDTO;
import com.cidenetshop.model.dto.MessageDTO;
import com.cidenetshop.model.dto.NewOrderDTO;
import com.cidenetshop.service.api.OrderServiceAPI;

@RestController
@RequestMapping(value = "/orders")
public class OrderRestController {

	private final OrderServiceAPI orderServiceAPI;

	@Autowired
	public OrderRestController(OrderServiceAPI orderServiceAPI) {
		super();
		this.orderServiceAPI = orderServiceAPI;
	}



	@PostMapping
	public ResponseEntity<MessageDTO> saveNewOrder(@RequestBody NewOrderDTO newOrder) {

		try {
			final Long idUser = ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getIdUser();

			orderServiceAPI.saveOrderAndSendEmail(idUser, newOrder);
			return new ResponseEntity<MessageDTO>(new MessageDTO("Order was created"), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()), HttpStatus.OK);

		}

	}

}
