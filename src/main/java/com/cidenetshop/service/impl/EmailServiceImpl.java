package com.cidenetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.Order;
import com.cidenetshop.model.entity.OrderDetail;
import com.cidenetshop.service.api.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	private JavaMailSender emailSender;

	@Autowired
	public EmailServiceImpl(JavaMailSender emailSender) {
		super();
		this.emailSender = emailSender;
	}

	public void SendEmailOrder(String to, String subject, Order order) {
		
		
		String contenido = "";
		
		for(OrderDetail orderDetail: order.getOrderDetails()) {
		 contenido = contenido + orderDetail.getProduct().getName() + "    " + orderDetail.getProduct().getPrice() + "  talla: " + orderDetail.getSize().getShortText() +  "  cantidad: "+ orderDetail.getQuantity() +"\n";
			
		}
		String body = "Hola te escribimos para decirte los detalles de tu compra.\n"
				+ "La direccion del pedido es: " + order.getOrderAddress()+"\n"
				+ contenido + "\n"
				+ "El costo total es: " + order.getTotalCost();
		
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("codigospruebas@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		emailSender.send(message);

	}

}
