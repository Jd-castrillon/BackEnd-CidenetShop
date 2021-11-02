package com.cidenetshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detalles_pedido")
public class Detalles_pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idrol;
	
	@Column(name ="cantidad")
	private Integer cantidad;
	
	@Column(name="precio_unidad")
	private Double precioUnidad;
	
	@Column(name="idproducto")
	private Long idProducto;
	
	@Column(name="idpedido")
	private Long idPedido;
	
	
	
	public Integer getIdrol() {
		return idrol;
	}



	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}



	public Integer getCantidad() {
		return cantidad;
	}



	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}



	public Double getPrecioUnidad() {
		return precioUnidad;
	}



	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}



	public Long getIdProducto() {
		return idProducto;
	}



	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}



	public Long getIdPedido() {
		return idPedido;
	}



	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}



	public Detalles_pedido(Integer idrol, Integer cantidad, Double precioUnidad, Long idProducto, Long idPedido) {
		super();
		this.idrol = idrol;
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
		this.idProducto = idProducto;
		this.idPedido = idPedido;
	}



	public Detalles_pedido() {
		super();
	}
	
	
	
	
}
