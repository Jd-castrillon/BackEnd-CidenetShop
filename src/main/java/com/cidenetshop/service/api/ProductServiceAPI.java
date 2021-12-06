package com.cidenetshop.service.api;

import java.util.List;

import com.cidenetshop.model.Product;
import dto.GetProductDTO;

public interface ProductServiceAPI {

	Product saveProduct(Product product) throws Exception;

	GetProductDTO findProductById(Long productId) throws Exception;

	Boolean deleteProductById(Long productId) throws Exception;

	List<GetProductDTO> getAllProducts();
	
	List<GetProductDTO> getProductByType(String productType);
	
	Product findById(Long productId);
	



}
