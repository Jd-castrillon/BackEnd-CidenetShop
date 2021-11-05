package com.cidenetshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

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
		List<Product> products = this.productServiceAPI.getAllProducts();
		return products;
	}

	@GetMapping(value = "/{productId}")
	public ResponseEntity<GetProductDTO> getProductById(@PathVariable("productId") Long productId) {
		try {
			return new ResponseEntity(this.productServiceAPI.findProductById(productId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

		try {
			Product obj = productServiceAPI.saveProduct(product);

			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
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

		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
