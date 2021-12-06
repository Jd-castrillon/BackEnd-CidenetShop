package com.cidenetshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cidenetshop.model.entity.OrderDetail;
import com.cidenetshop.model.embeddable.OrderDetailKey;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, OrderDetailKey>{
	
	
	
}
