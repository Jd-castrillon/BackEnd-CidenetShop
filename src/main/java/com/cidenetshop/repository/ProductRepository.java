package com.cidenetshop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cidenetshop.model.Product;

public interface ProductRepository extends CrudRepository<Product,Long>{
	
	
	@Query(nativeQuery = true, value = "select * from products  left join product_types  on products.id_product_type  =  product_types.id WHERE product_types.product_type = :productType")
	Iterable<Product> findAllByProductType(@Param("productType") String productType);
	
	
}
