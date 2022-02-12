package com.cidenetshop.service.api;

import java.util.List;

import com.cidenetshop.model.dto.GetProductDTO;
import com.cidenetshop.model.dto.NewProductDTO;
import com.cidenetshop.model.entity.Product;

public interface ProductServiceAPI {

	Product saveProduct(Product product) throws Exception;

	GetProductDTO findProductById(Long productId) throws Exception;

	Boolean deleteProductById(Long productId) throws Exception;

	List<GetProductDTO> getAllProducts();

	List<GetProductDTO> RankingOfProducts();

	List<GetProductDTO> getProductByGender(String productType);

	Product findById(Long productId);
	
	void saveNewProduct( NewProductDTO newProduct , byte[] picture ) throws Exception;
	
	
	
	

}
