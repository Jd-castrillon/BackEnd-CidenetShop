package com.cidenetshop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cidenetshop.model.entity.Size;

public interface SizeRepository extends CrudRepository<Size, Integer> {
		
	Optional<Size> findByShortText(String shortText);
}
