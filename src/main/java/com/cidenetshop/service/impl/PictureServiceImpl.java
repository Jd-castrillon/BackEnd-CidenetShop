package com.cidenetshop.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.Picture;
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

//	@Override
//	public Picture savePicture(Picture picture) throws Exception {
//		if (true) {
//			throw new Exception("No esta llegando la imagen");
//		} else if (picture.getProduct() == null) {
//			throw new Exception("No hay producto relacionado a la imagen");
//
//		}
//		try {
//			return this.pictureRepository.save(picture);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception("Ha ocurrido un error inesperado al guardar la imagen en la base de datos."
//					+ picture.getProduct().getId());
//		}
//
//	}

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
			new Exception("Imagen no encontrada para el id " + id);
		}

//		final Picture pictureFound = repoResponse.get();
//
//		return pictureFound.getPicture();}

		return null;

	}

	@Override
	public Picture savePicture(Picture picture) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
