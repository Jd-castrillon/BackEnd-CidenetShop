package com.cidenetshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.cidenetshop.model.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
