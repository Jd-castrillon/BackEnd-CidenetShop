package com.cidenetshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cidenetshop.model.dto.GetAdminProductDTO;
import com.cidenetshop.model.dto.GetProductDTO;
import com.cidenetshop.model.dto.MessageDTO;
import com.cidenetshop.model.dto.NewProductDTO;
import com.cidenetshop.service.api.ProductServiceAPI;

@RestController
@RequestMapping(value = "/products")

public class ProductRestController {

	private final ProductServiceAPI productServiceAPI;

	@Autowired
	public ProductRestController(ProductServiceAPI productServiceAPI) {
		this.productServiceAPI = productServiceAPI;
	}
	
	
	@GetMapping(value = "/ranking")
	public List<GetProductDTO> getAllProducts() {
		List<GetProductDTO> products = this.productServiceAPI.RankingOfProducts();
		return products;
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping
	public List<GetAdminProductDTO> getRankingProducts() {
		List<GetAdminProductDTO> products = this.productServiceAPI.getAllProducts();
		return products;
	}

	@GetMapping(value="/active")
	public ResponseEntity<?> getActiveProducts() {
		try {
			List<GetProductDTO> activeProducts = productServiceAPI.getActiveProducts();
			return new ResponseEntity<Object>(activeProducts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()),HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(value = "/id/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") Long productId) {
		try {
			return new ResponseEntity<GetProductDTO>(this.productServiceAPI.findProductById(productId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/active/{gender}")
	public List<GetProductDTO> getProductByGender(@PathVariable("gender") String gender) {
		List<GetProductDTO> products = this.productServiceAPI.getProductByGender(gender);
		return products;
	}

	@PreAuthorize("hasAuthority('admin')")
	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<MessageDTO> saveProduct(@RequestPart("picture") MultipartFile picture,
			@RequestPart("newProduct") NewProductDTO newProduct) {

		try {

			productServiceAPI.saveNewProduct(newProduct, picture);

			return new ResponseEntity<MessageDTO>(new MessageDTO("Product was created"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PreAuthorize("hasAuthority('admin')")
	@PutMapping(value = "/{idProduct}", consumes = { "multipart/form-data" })
	public ResponseEntity<MessageDTO> updateProduct(@RequestPart("picture") MultipartFile picture,
			@RequestPart("updateProduct") NewProductDTO updateProduct, @PathVariable("idProduct") Long idProduct) {
		try {
			productServiceAPI.updateProduct(updateProduct, picture, idProduct);
			return new ResponseEntity<MessageDTO>(new MessageDTO("Product was update"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasAuthority('admin')")
	@DeleteMapping(value = "/{productId}")
	public ResponseEntity<MessageDTO> deleteProductById(@PathVariable("productId") Long productId) {

		try {
			productServiceAPI.deleteProductById(productId);

			return new ResponseEntity<MessageDTO>(new MessageDTO("Product was delete"), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
		}

	}

}
