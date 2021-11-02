package com.cidenetshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproducto;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="color")
	private String color;
	
	@Column(name="precio")
	private Double precio;
	
	@Column(name="cantidad_existente")
	private Integer cantidadExistente;
	
	@Column(name="tipo_producto")
	private String tipoProducto;
	
	@Column(name="idtalla")
	private Integer idTalla;

	public Long getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Long idproducto) {
		this.idproducto = idproducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getCantidadExistente() {
		return cantidadExistente;
	}

	public void setCantidadExistente(Integer cantidadExistente) {
		this.cantidadExistente = cantidadExistente;
	}

	public String getColor() {
		return color;
	}

	public void setIdColor(String color) {
		this.color = color;
	}

	public Integer getIdTalla() {
		return idTalla;
	}

	public void setIdTalla(Integer idTalla) {
		this.idTalla = idTalla;
	}

	public Producto(Long idproducto, String nombre, String descripcion, Double precio, Integer cantidadExistente,
			String color, Integer idTalla) {
		super();
		this.idproducto = idproducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidadExistente = cantidadExistente;
		this.color = color;
		this.idTalla = idTalla;
	}

	public Producto() {
		super();
	}
	
	
	
}
