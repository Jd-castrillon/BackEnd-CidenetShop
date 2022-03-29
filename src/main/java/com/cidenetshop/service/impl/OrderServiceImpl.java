package com.cidenetshop.service.impl;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.dto.GetOrderDetailDTO;
import com.cidenetshop.model.dto.NewOrderDTO;
import com.cidenetshop.model.entity.ExistingQuantity;
import com.cidenetshop.model.entity.Order;
import com.cidenetshop.model.entity.OrderDetail;
import com.cidenetshop.model.entity.Product;
import com.cidenetshop.model.entity.Size;
import com.cidenetshop.model.entity.User;
import com.cidenetshop.repository.OrderRepository;
import com.cidenetshop.repository.UserRepository;
import com.cidenetshop.service.api.EmailService;
import com.cidenetshop.service.api.ExistingQuantityServiceAPI;
import com.cidenetshop.service.api.OrderServiceAPI;
import com.cidenetshop.service.api.ProductServiceAPI;
import com.cidenetshop.service.api.SizeServiceAPI;

@Service
public class OrderServiceImpl implements OrderServiceAPI {

    private final ExistingQuantityServiceAPI existingQuantityServiceAPI;

    private final OrderRepository orderRepository;

    private final ProductServiceAPI productServiceAPI;

    private final ModelMapper modelMapper;

    private final EmailService emailService;

    private final UserRepository userRepository;

    private final SizeServiceAPI sizeServiceAPI;

    @Autowired
    public OrderServiceImpl(ExistingQuantityServiceAPI existingQuantityServiceAPI, OrderRepository orderRepository,
                            ProductServiceAPI productServiceAPI, ModelMapper modelMapper, EmailService emailService,
                            UserRepository userRepository, SizeServiceAPI sizeServiceAPI) {
        super();
        this.existingQuantityServiceAPI = existingQuantityServiceAPI;
        this.orderRepository = orderRepository;
        this.productServiceAPI = productServiceAPI;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.sizeServiceAPI = sizeServiceAPI;
    }


    @Override
    public void saveOrderAndSendEmail(Long idUser, NewOrderDTO newOrder) throws Exception {

        Order order = createOrder(idUser, newOrder);

        updateStockByBuy(order.getOrderDetails());

        orderRepository.save(order);
        emailService.SendEmailOrder(userRepository.findById(idUser).get().getEmail(), "Compra Cidenet", order);
    }

    @Transactional
    private void updateStockByBuy(List<OrderDetail> orderDetails) throws Exception {

        for (OrderDetail orderDetail : orderDetails) {

            final ExistingQuantity stock = existingQuantityServiceAPI.findByProductIdAndShortText(
                    orderDetail.getProduct().getId(), orderDetail.getSize().getShortText());

            if (stock.getExistingQuantity() - orderDetail.getQuantity() < 0)
                throw new Exception("Don't can set negative value to setExistingQuantity");

            stock.setExistingQuantity(stock.getExistingQuantity() - orderDetail.getQuantity());

        }

    }

    private Order createOrder(Long idUser, NewOrderDTO newOrder) throws Exception {
        final User user = new User();
        user.setIdUser(idUser);

        final Order order = new Order();
        order.setOrderAddress(newOrder.getOrderAddress());
        order.setOrderDate(LocalDate.now(Clock.system(ZoneId.of("America/Bogota"))));
        order.setUser(user);
        order.setOrderDetails(new ArrayList<>());

        addOrderDetailsAndCostTotalToOrder(newOrder.getOrderDetails(), order);

        return order;
    }

    private void addOrderDetailsAndCostTotalToOrder(List<GetOrderDetailDTO> orderDetailsDTO, Order order) throws Exception {

        for (GetOrderDetailDTO orderDetailDTO : orderDetailsDTO) {

            final Product product = productServiceAPI.findById(orderDetailDTO.getIdProduct());
            final Size size = sizeServiceAPI.findByShortText(orderDetailDTO.getSize());
            final ExistingQuantity stock = existingQuantityServiceAPI.findByProductIdAndShortText(product.getId(),
                    orderDetailDTO.getSize());

            if (stock.getExistingQuantity() <= 0)
                throw new Exception("there aren't stock for product with id " + orderDetailDTO.getIdProduct()
                        + " and size " + orderDetailDTO.getSize());

            if (orderDetailDTO.getQuantity() > stock.getExistingQuantity())
                throw new Exception("the requested quantity is greater than the stock in the product with id "
                        + orderDetailDTO.getIdProduct() + " and size " + orderDetailDTO.getSize());

            final OrderDetail orderDetail = new OrderDetail(product, order, size, orderDetailDTO.getQuantity(), product.getPrice());

            order.setTotalCost(order.getTotalCost() + (orderDetail.getSalePrice() * orderDetail.getQuantity()));

            order.getOrderDetails().add(orderDetail);
        }
    }


}
