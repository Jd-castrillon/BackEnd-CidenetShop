package com.cidenetshop.service.api;

import java.util.List;
import java.util.Optional;

import com.cidenetshop.model.dto.DeleteExistingQuantityDTO;
import com.cidenetshop.model.dto.GetExistingQuantityDTO;
import com.cidenetshop.model.dto.NewExistingQuantityDTO;
import com.cidenetshop.model.entity.ExistingQuantity;

public interface ExistingQuantityServiceAPI {

	ExistingQuantity findByProductIdAndSizeId(Long idProduct, Long idSize) throws Exception;
	
	ExistingQuantity findByProductIdAndShortText(Long idProduct , String shortText) throws Exception;
	
	List<GetExistingQuantityDTO> findByIdProduct(Long idProduct);
	
	Optional<ExistingQuantity> findByIdProductAndShortText(Long idProdiuct , String idSize) throws Exception;
	
	void saveExistingQuantity(NewExistingQuantityDTO newExistingQuantityDTO  ) throws Exception;
	
	void updateStock(NewExistingQuantityDTO updateExistingQuantityDTO ) throws Exception;
	
	void deleteExistingQuantity(DeleteExistingQuantityDTO deleteExistingQuantityDTO) throws Exception; 
}
