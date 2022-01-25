package com.cidenetshop.service.api;

import com.cidenetshop.model.entity.ExistingQuantity;

public interface ExistingQuantityServiceAPI {

	ExistingQuantity findByProductIdAndSizeId(Long idProduct, Long idSize);
	
	ExistingQuantity findByProductIdAndShortText(Long idProduct , String shortText);
}
