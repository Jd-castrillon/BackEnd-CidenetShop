package com.cidenetshop.service.api;

import com.cidenetshop.model.dto.GetOrderDTO;
import com.cidenetshop.model.dto.NewOrderDTO;
import com.cidenetshop.model.entity.Order;

public interface OrderServiceAPI {

    void saveOrder(Long idUser, NewOrderDTO newOrder) throws Exception;

    Order findById(Long id);

    GetOrderDTO findOrderById(Long orderId) throws Exception;

}
