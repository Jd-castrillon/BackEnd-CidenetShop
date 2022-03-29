package com.cidenetshop.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cidenetshop.model.entity.Picture;
import com.cidenetshop.model.entity.Product;
import com.cidenetshop.repository.PictureRepository;
import com.cidenetshop.service.api.PictureServiceAPI;

import com.cidenetshop.model.dto.GetPictureDTO;

@Service
public class PictureServiceImpl implements PictureServiceAPI {

	private PictureRepository pictureRepository;

	@Autowired
	public PictureServiceImpl(PictureRepository pictureRepository) {
		super();
		this.pictureRepository = pictureRepository;
	}

	@Override
	public GetPictureDTO findPictureById(Long id) throws Exception {

		try {

			Optional<Picture> repoResponse = pictureRepository.findById(id);

			Picture pictureFound = repoResponse.get();
			ModelMapper modelMapper = new ModelMapper();
			GetPictureDTO getPictureDTO = modelMapper.map(pictureFound, GetPictureDTO.class);
			return getPictureDTO;
		} catch (Exception e) {
			e.printStackTrace();
		}

//		if (repoResponse.isEmpty() || id == null) {
//			new Exception("Imagen no encontrada para el id " + id);
//		}

		return null;

	}

	public byte[] findPictureBlobById(Long id) throws Exception {
		final Optional<Picture> repoResponse = this.pictureRepository.findById(id);

		if (repoResponse.isEmpty() || id == null) {
			throw new Exception("Imagen no encontrada para el id " + id);
		}

		return null;

	}

	@Override
	public void savePicture(Product product, MultipartFile picture) throws Exception {

		if (picture.isEmpty())
			throw new Exception("Image is needed");

		Picture newPicture = new Picture();

		newPicture.setProduct(product);

		newPicture.setPicture(picture.getBytes());

		pictureRepository.save(newPicture);

	}

	@Override
	public Picture findById(Long id) throws Exception {
		Optional<Picture> response = pictureRepository.findById(id);

		if (response.isEmpty())
			throw new Exception("Picture don't found with id:  " + id);

		return response.get();
	}
	
	@Transactional
	@Override
	public void updatePicture(Long id, MultipartFile updatePicture) throws Exception {

		if (updatePicture.isEmpty())
			throw new Exception("Image is needed");
		
		Optional<Picture> response = pictureRepository.findById(id);
		
		if(response.isEmpty()) {
			Picture picture = new Picture();
			picture.setId(id);
			picture.setPicture(updatePicture.getBytes());			
			
		}
			
		Optional<Picture> response2 = pictureRepository.findById(id);

		if (response2.isEmpty()) {
			throw new Exception("Can't update image");
			
		}
	
		
		

	}

}
