package com.cidenetshop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.ExistingQuantity;
import com.cidenetshop.model.entity.Size;
import com.cidenetshop.repository.ExistingQuantityRepository;
import com.cidenetshop.service.api.ExistingQuantityServiceAPI;
import com.cidenetshop.service.api.SizeServiceAPI;

@Service
public class ExistingQuantityServiceImpl implements ExistingQuantityServiceAPI {

	private final ExistingQuantityRepository existingQuantityRepository;
	private final SizeServiceAPI sizeServiceAPI; 

	@Autowired
	public ExistingQuantityServiceImpl(ExistingQuantityRepository existingQuantityRepository, SizeServiceAPI sizeServiceAPI) {
		super();
		this.existingQuantityRepository = existingQuantityRepository;
		this.sizeServiceAPI = sizeServiceAPI;
	}

	@Override
	public ExistingQuantity findByProductIdAndSizeId(Long idProduct, Integer idSize) {
		Optional<ExistingQuantity> existingQuantity = existingQuantityRepository.findByProductIdAndSizeId(idProduct, idSize);
		
		if (existingQuantity.isPresent()) {
			return existingQuantity.get();
		}
		
		return null;
	}

	@Override
	public ExistingQuantity findByProductIdAndShortText(Long idProduct, String shortText) {

		Size size = sizeServiceAPI.findByShortText(shortText);
		
		return findByProductIdAndSizeId(idProduct, size.getId());
	}
	
	
	
	

}
