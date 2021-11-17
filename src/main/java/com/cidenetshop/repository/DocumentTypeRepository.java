package com.cidenetshop.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cidenetshop.model.DocumentType;

public interface DocumentTypeRepository extends CrudRepository<DocumentType, Integer> {
		
	Optional<DocumentType> findByDocumentType(String documentType);
}
