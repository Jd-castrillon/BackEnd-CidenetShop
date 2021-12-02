package com.cidenetshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cidenetshop.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long>{
	
}
