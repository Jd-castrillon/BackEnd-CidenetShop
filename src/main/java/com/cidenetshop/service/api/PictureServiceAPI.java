package com.cidenetshop.service.api;

import com.cidenetshop.model.entity.Picture;
import com.cidenetshop.model.entity.Product;

import org.springframework.web.multipart.MultipartFile;

import com.cidenetshop.model.dto.GetPictureDTO;

public interface PictureServiceAPI {
	
	void savePicture (Product product , MultipartFile picture) throws Exception;
	
	GetPictureDTO findPictureById(Long id) throws Exception;
	
	Picture findById(Long id) throws Exception;
	
	byte[] findPictureBlobById(Long id) throws Exception;
	
	void updatePicture(Long id , MultipartFile updatePicture ) throws Exception;
	
	
}
