package com.cidenetshop.service.api;

import java.util.List;

import com.cidenetshop.model.dto.GetExistingQuantityDTO;
import com.cidenetshop.model.dto.NewExistingQuantityDTO;
import com.cidenetshop.model.entity.ExistingQuantity;

public interface ExistingQuantityServiceAPI {

	ExistingQuantity findByProductIdAndSizeId(Long idProduct, Long idSize) throws Exception;
	
	ExistingQuantity findByProductIdAndShortText(Long idProduct , String shortText) throws Exception;
	
	List<GetExistingQuantityDTO> findByIdProduct(Long idProduct);
	
	void saveExistingQuantity(NewExistingQuantityDTO newExistingQuantityDTO  ) throws Exception;
	
	void updateStock(Long idProduct , String shortText, Integer quantity) throws Exception;
}
