package com.cidenetshop.service.api;

import java.util.List;

import com.cidenetshop.model.Product;

public interface ProductServiceAPI {

	Product saveProduct(Product product) throws Exception;

	Product findProductById(Long productId) throws Exception;

	Boolean deleteProductById(Long productId) throws Exception;

	List<Product> getAllProducts() throws Exception;
	
	

}
