package com.cidenetshop.service.api;

import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.Order;

@Service
public interface EmailService {
	
	public void SendEmailOrder(String to, String subject, Order order);
}
