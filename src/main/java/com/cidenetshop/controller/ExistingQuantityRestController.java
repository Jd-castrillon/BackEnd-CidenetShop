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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.model.dto.DeleteExistingQuantityDTO;
import com.cidenetshop.model.dto.GetExistingQuantityDTO;
import com.cidenetshop.model.dto.MessageDTO;
import com.cidenetshop.model.dto.NewExistingQuantityDTO;
import com.cidenetshop.service.api.ExistingQuantityServiceAPI;

@RestController
@RequestMapping(value = "/existingQuantity")
public class ExistingQuantityRestController {

	private final ExistingQuantityServiceAPI existingQuantityServiceAPI;

	@Autowired
	public ExistingQuantityRestController(ExistingQuantityServiceAPI existingQuantityServiceAPI) {
		super();
		this.existingQuantityServiceAPI = existingQuantityServiceAPI;
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping(value = "/{idProduct}")
	public ResponseEntity<?> getExistingQuantityByProduct(@PathVariable("idProduct") Long idProduct) {

		try {

			List<GetExistingQuantityDTO> existingQuantityDTOs = existingQuantityServiceAPI.findByIdProduct(idProduct);

			return new ResponseEntity<Object>(existingQuantityDTOs, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MessageDTO(e.getMessage()), HttpStatus.OK);
		}

	}
	
	@PreAuthorize("hasAuthority('admin')")
	@PostMapping
	public ResponseEntity<?> saveExistingQuantity(@RequestBody NewExistingQuantityDTO newExistingQuantityDTO) {

		try {
			existingQuantityServiceAPI.saveExistingQuantity(newExistingQuantityDTO);
			return new ResponseEntity<Object>(new MessageDTO("ExistingQuantity created"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new MessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@PutMapping(value = "/{idProduct}")
	public ResponseEntity<?> updateExistingQuantity(@RequestBody NewExistingQuantityDTO updateExistingQuantity) {
		try {
			existingQuantityServiceAPI.updateStock(updateExistingQuantity);

			return new ResponseEntity<MessageDTO>(new MessageDTO("Stock was update"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new MessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);

		}

	}
	
	@PreAuthorize("hasAuthority('admin')")
	@DeleteMapping
	public ResponseEntity<?> deleteExistingQuantity(@RequestBody DeleteExistingQuantityDTO deleteExistingQuantityDTO) {
		try {
			existingQuantityServiceAPI.deleteExistingQuantity(deleteExistingQuantityDTO);

			return new ResponseEntity<MessageDTO>(new MessageDTO("Stock was delete"), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()), HttpStatus.OK);
		
		}
	}

}
