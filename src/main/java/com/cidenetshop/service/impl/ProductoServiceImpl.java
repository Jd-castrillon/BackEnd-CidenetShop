package com.cidenetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.cidenetshop.commons.GenericServiceImpl;
import com.cidenetshop.dao.ProductoDaoAPI;
import com.cidenetshop.model.Producto;
import com.cidenetshop.service.api.ProductoServiceAPI;

public class ProductoServiceImpl extends GenericServiceImpl<Producto,Long> implements ProductoServiceAPI {
	
	@Autowired
	ProductoDaoAPI productoDaoAPI;
	
	@Override
	public CrudRepository<Producto, Long> getDao() {
		// TODO Auto-generated method stub
		return productoDaoAPI;
	}

}
