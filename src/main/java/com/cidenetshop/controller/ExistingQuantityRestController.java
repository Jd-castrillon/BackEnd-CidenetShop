package com.cidenetshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(value = "/{idProduct}")
	public ResponseEntity<?> getExistingQuantityByProduct(@PathVariable("idProduct") Long idProduct) {

		try {

			List<GetExistingQuantityDTO> existingQuantityDTOs = existingQuantityServiceAPI.findByIdProduct(idProduct);

			return new ResponseEntity<Object>(existingQuantityDTOs, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MessageDTO(e.getMessage()), HttpStatus.OK);
		}

	}
	
	@PostMapping
	public ResponseEntity<?> saveExistingQuantity(@RequestBody NewExistingQuantityDTO newExistingQuantityDTO){
		
		try {
			existingQuantityServiceAPI.saveExistingQuantity(newExistingQuantityDTO);
			return new ResponseEntity<Object>(new MessageDTO("existingQuantity created"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new MessageDTO(e.getMessage()), HttpStatus.OK);
		}
		
		
	}
	

}
