package com.cidenetshop.service.api;

import com.cidenetshop.model.Picture;

import dto.GetPictureDTO;

public interface PictureServiceAPI {
	
	Picture savePicture (Picture picture) throws Exception;
	
	GetPictureDTO findPictureById(Long id) throws Exception;
	
	
}
