package com.cidenetshop.service.api;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.cidenetshop.model.dto.GetProductDTO;
import com.cidenetshop.model.dto.NewProductDTO;
import com.cidenetshop.model.entity.Product;

public interface ProductServiceAPI {

	GetProductDTO findProductById(Long productId) throws Exception;

	Boolean deleteProductById(Long productId) throws Exception;

	List<GetProductDTO> getAllProducts();

	List<GetProductDTO> RankingOfProducts();

	List<GetProductDTO> getProductByGender(String productType);

	Product findById(Long productId) throws Exception;

	Optional<Product> findByName(String name) throws Exception;

	void saveNewProduct(NewProductDTO newProduct, MultipartFile picture) throws Exception;

	void updateProduct(NewProductDTO updateProduct, MultipartFile updatePicture, Long idProduct) throws Exception;

}
