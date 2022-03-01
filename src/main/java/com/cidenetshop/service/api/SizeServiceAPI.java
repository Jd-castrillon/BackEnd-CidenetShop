package com.cidenetshop.service.api;

import com.cidenetshop.model.entity.Size;

public interface SizeServiceAPI {

	Size findByShortText(String shortText) throws Exception;
	
	
}
