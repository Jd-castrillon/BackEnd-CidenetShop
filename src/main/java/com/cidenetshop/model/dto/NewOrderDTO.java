package com.cidenetshop.model.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewOrderDTO {
    DateTimeFormatter isoHora = DateTimeFormatter.ISO_LOCAL_TIME;

    private String orderAddress;

    private List<GetOrderDetailDTO> orderDetails;

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public List<GetOrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<GetOrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }


}