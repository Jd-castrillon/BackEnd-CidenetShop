package com.cidenetshop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cidenetshop.model.entity.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long>{
		
	Optional<Picture> findById(Long id);
	
}
