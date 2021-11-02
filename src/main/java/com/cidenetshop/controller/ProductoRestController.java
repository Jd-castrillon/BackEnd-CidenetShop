package com.cidenetshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cidenetshop.model.Producto;
import com.cidenetshop.service.api.ProductoServiceAPI;

@RestController
@RequestMapping(value="/producto")

public class ProductoRestController {
	
	ProductoServiceAPI productoServiceAPI;
	
	@GetMapping(value ="/all")
	public List<Producto> getAll(){
		return productoServiceAPI.getAll();
	}
	
	@GetMapping(value="/find/{idproducto}") 
	public Producto findProducto(@PathVariable Long idproducto) {
		return productoServiceAPI.get(idproducto);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<Producto> saveProducto (@ModelAttribute Producto producto){
		
		Producto obj = productoServiceAPI.save(producto);
		return new ResponseEntity<Producto> (obj, HttpStatus.OK);
	}
	
	@GetMapping(value="/delete/{idproducto}")
	public ResponseEntity<Producto> deleteProducto(@PathVariable Long idproducto){
		
		Producto producto = productoServiceAPI.get(idproducto);
		if(producto != null) {
			productoServiceAPI.delete(idproducto);	
		}else {
			return new ResponseEntity<Producto>(producto, HttpStatus.INTERNAL_SERVER_ERROR) ; 
		}
		return new ResponseEntity<Producto> (producto, HttpStatus.OK);
	} 
	
	
	
	}
