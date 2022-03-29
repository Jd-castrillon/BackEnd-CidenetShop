package com.cidenetshop.service.api;

import org.springframework.stereotype.Service;

import com.cidenetshop.model.dto.NewOrderDTO;

@Service
public interface OrderServiceAPI {

    void saveOrderAndSendEmail(Long idUser, NewOrderDTO newOrder) throws Exception;



}
