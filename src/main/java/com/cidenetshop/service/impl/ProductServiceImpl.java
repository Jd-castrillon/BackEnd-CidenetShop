package com.cidenetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.Product;
import com.cidenetshop.repository.PictureRepository;
import com.cidenetshop.repository.ProductRepository;
import com.cidenetshop.service.api.PictureServiceAPI;
import com.cidenetshop.service.api.ProductServiceAPI;

import dto.GetProductDTO;
import dto.GetExistingQuantityDTO;

@Service
public class ProductServiceImpl implements ProductServiceAPI {

	private final ProductRepository productRepository;
	
	private final PictureServiceAPI pictureServiceAPI;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, PictureServiceAPI pictureServiceAPI) {
		this.productRepository = productRepository;
		this.pictureServiceAPI = pictureServiceAPI;
		;
	}

	@Override

	public Product saveProduct(Product product) throws Exception {

		if (product.getPrice() == null) {

			throw new Exception("El valor de producto tiene que ser diferente de nulo");
		} else if (product.getPrice() <= 0) {
			throw new Exception("El valor del producto tiene que ser mayor que Cero");
		}

		try {
			return this.productRepository.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ha ocurrido un error inesperado al guardar el producto en la base de datos.");
		}
	}

	@Override
	public GetProductDTO findProductById(Long productId) throws Exception {

		final Optional<Product> repoResponse = this.productRepository.findById(productId);

		if (repoResponse.isEmpty()) {
			throw new Exception("Producto no encontrado para el id " + productId);
		}

		final Product productFound = repoResponse.get();

		
		return convertProductToDTO(productFound);
	}

	@Override
	public Boolean deleteProductById(Long productId) throws Exception {

		if (findProductById(productId) != null) {
			try {
				this.productRepository.deleteById(productId);
				return true;
			} catch (Exception e) {
				throw new Exception("Hubo un error eliminando el producto, intentalo nuevamente mas tarde");

			}
		}
		return false;
	}

	@Override
	public List<GetProductDTO> getAllProducts() {
		
		List<GetProductDTO> productsDTO = new ArrayList<>();
		this.productRepository.findAll().forEach(obj -> {
			try {
				productsDTO.add(convertProductToDTO(obj));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});


		return productsDTO;
	}

	private GetProductDTO convertProductToDTO(Product product) throws Exception {
		
		ModelMapper modelMapper = new ModelMapper();

		GetProductDTO getProductDTO = modelMapper.map(product, GetProductDTO.class);
		
		getProductDTO.setPicture(pictureServiceAPI.findPictureById(product.getId()));

		return getProductDTO;
	}

}
