package com.cidenetshop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.Gender;
import com.cidenetshop.repository.GenderRepository;
import com.cidenetshop.service.api.GenderServiceAPI;

@Service
public class GenderServiceImpl implements GenderServiceAPI {

	final private GenderRepository genderRepository;
	
	@Autowired
	public GenderServiceImpl(GenderRepository genderRepository) {
		super();
		this.genderRepository = genderRepository;
	}

	@Override
	public Gender findByGender(String gender) throws Exception {
		
		if(gender.equals("") || gender.equals(null))
			throw new Exception("Gender needed");
		
		Optional<Gender> response = genderRepository.findByGender(gender);
		
		if (response.isEmpty())
			throw new Exception("Don't found gender "+ gender);
		
		if(response.isPresent())
			return response.get();
		
		return null;
	}

}
