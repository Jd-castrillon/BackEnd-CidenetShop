package com.cidenetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cidenetshop.model.entity.Picture;
import com.cidenetshop.service.api.PictureServiceAPI;

import com.cidenetshop.model.dto.GetPictureDTO;

@RestController
@RequestMapping(value ="/pictures")
public class pictureRestController {
	
	
	private PictureServiceAPI pictureServiceAPI;
	
	@Autowired
	public pictureRestController(PictureServiceAPI pictureServiceAPI) {
		super();
		this.pictureServiceAPI = pictureServiceAPI;
	}


	@GetMapping(value="{pictureId}")
	public ResponseEntity<GetPictureDTO> getPictureById(@PathVariable("pictureId") Long id ){
	
	try {
		return new ResponseEntity(this.pictureServiceAPI.findPictureById(id), HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	}
	
	
	@PostMapping
	public ResponseEntity<Picture> savePicture(@RequestParam("file") MultipartFile file , @RequestBody Long id ){
		try {
			byte[] picture1= file.getBytes();
			
			Picture picture = new Picture();
			
			picture.setId(id);
			
			picture.setPicture(file.getBytes());
			
			this.pictureServiceAPI.savePicture(picture);
			
			return new ResponseEntity(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	
	
	}
	

