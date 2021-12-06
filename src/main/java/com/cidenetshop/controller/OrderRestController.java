package com.cidenetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.service.api.OrderServiceAPI;

import dto.GetOrderDTO;
import dto.MessageDTO;
import dto.NewOrderDTO;

@RestController
@RequestMapping(value="/orders")
public class OrderRestController {
	
	private final OrderServiceAPI orderServiceAPI;
	
	@Autowired
	public OrderRestController(OrderServiceAPI orderServiceAPI) {
		super();
		this.orderServiceAPI = orderServiceAPI;
	}
	
	@GetMapping(value="/{orderId}")
	public ResponseEntity<GetOrderDTO> getOrderById(@PathVariable("orderId") Long orderId )  {
		try {
			return new ResponseEntity(this.orderServiceAPI.findOrderById(orderId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	} 
	
	}
	
	@PostMapping
	public ResponseEntity<GetOrderDTO> saveNewOrder(@RequestBody NewOrderDTO newOrder, BindingResult bindingResult ){
		if(bindingResult.hasErrors())
			return new ResponseEntity(new MessageDTO("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		try {
			orderServiceAPI.saveOrder(newOrder);
			return new ResponseEntity(new MessageDTO("Orden guardada"), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

		}
		
		
	} 
	
}
