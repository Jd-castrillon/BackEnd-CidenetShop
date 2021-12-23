package com.cidenetshop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.Size;
import com.cidenetshop.repository.SizeRepository;
import com.cidenetshop.service.api.SizeServiceAPI;

@Service
public class SizeServiceImpl implements SizeServiceAPI {

	private final SizeRepository sizeRepository;

	@Autowired
	public SizeServiceImpl(SizeRepository sizeRepository) {
		super();
		this.sizeRepository = sizeRepository;
	}

	@Override
	public Size findByShortText(String shortText) {

		Optional<Size> size = sizeRepository.findByShortText(shortText);

		if (size.isPresent())
			return size.get();

		return null;
	}

}
