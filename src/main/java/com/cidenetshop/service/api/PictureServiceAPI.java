package com.cidenetshop.service.api;

import com.cidenetshop.model.entity.Picture;

import com.cidenetshop.model.dto.GetPictureDTO;

public interface PictureServiceAPI {
	
	Picture savePicture (Picture picture) throws Exception;
	
	GetPictureDTO findPictureById(Long id) throws Exception;
	
	byte[] findPictureBlobById(Long id) throws Exception;
	
	
}
