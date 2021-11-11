package com.cidenetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.Product;
import com.cidenetshop.repository.ProductRepository;
import com.cidenetshop.service.api.ProductServiceAPI;

import dto.GetProductDTO;
import dto.pepe;

@Service
public class ProductServiceImpl implements ProductServiceAPI {

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
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

		final GetProductDTO dto = new GetProductDTO();
		dto.setId(productFound.getId());
		dto.setName(productFound.getName());
		dto.setDescription(productFound.getDescription());
		dto.setPrice(productFound.getPrice());
		dto.setProductType(productFound.getProductType().getProductType());
		final List<pepe> listpepe = new ArrayList<>();
		productFound.getExistingQuantity().forEach((eq) -> {
			listpepe.add(new pepe(eq.getSize().getId(), eq.getSize().getShortText(), eq.getExistingQuantity()));
		});
		dto.setPepe(listpepe);

		return dto;
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
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		this.productRepository.findAll().forEach(obj -> products.add(obj));

		List<GetProductDTO> listProductGetDTo = new ArrayList<>();

		return products;
	}

}
