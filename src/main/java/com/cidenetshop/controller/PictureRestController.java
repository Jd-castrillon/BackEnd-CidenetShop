package com.cidenetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.model.dto.GetPictureDTO;
import com.cidenetshop.model.dto.MessageDTO;
import com.cidenetshop.service.api.PictureServiceAPI;

@RestController
@RequestMapping(value = "/pictures")
public class PictureRestController {

	private PictureServiceAPI pictureServiceAPI;

	@Autowired
	public PictureRestController(PictureServiceAPI pictureServiceAPI) {
		super();
		this.pictureServiceAPI = pictureServiceAPI;
	}

	@GetMapping(value = "{pictureId}")
	public ResponseEntity<?> getPictureById(@PathVariable("pictureId") Long id) {

		try {
			return new ResponseEntity<GetPictureDTO>(this.pictureServiceAPI.findPictureById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageDTO>(new MessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	

}
