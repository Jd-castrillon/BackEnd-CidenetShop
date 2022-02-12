package com.cidenetshop.service.api;

import org.springframework.stereotype.Service;

import com.cidenetshop.model.entity.Gender;

@Service
public interface GenderServiceAPI {

	Gender findByGender(String gender) throws Exception;
}
