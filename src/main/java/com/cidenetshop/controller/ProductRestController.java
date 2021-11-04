package com.cidenetshop.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.model.Product;
import com.cidenetshop.service.api.ProductServiceAPI;

import dto.GetProductDTO;

@RestController
@RequestMapping(value = "/products")

public class ProductRestController {

	private final ProductServiceAPI productServiceAPI;

	@Autowired
	public ProductRestController(ProductServiceAPI productServiceAPI) {
		this.productServiceAPI = productServiceAPI;
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return null;
	}

	@GetMapping(value = "/{productId}")
	@Transactional
	public ResponseEntity<GetProductDTO> getProductById(@PathVariable("productId") Long productId) {
		try {
			final Product product = this.productServiceAPI.findProductById(productId);

			final GetProductDTO dto = new GetProductDTO();
			dto.setName(product.getName());
			dto.setSize(product.getSize().getShortText());

			return new ResponseEntity(dto, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Product> saveProduct(@ModelAttribute Product product) {

		try {
			Product obj = productServiceAPI.saveProduct(product);
			return new ResponseEntity<Product>(obj, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@DeleteMapping
	public List<Product> deleteAllProducts() {
		return null;
	}

	@DeleteMapping(value = "/{productId}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("productId") Long productId) {

		try {
			final Boolean response = this.productServiceAPI.deleteProductById(productId);
			if (response) {
				return new ResponseEntity<Product>(HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		return  new ResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
