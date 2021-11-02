package com.cidenetshop.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idpedido;
	
	@Column(name="direccion_pedido")
	private String direccionPedido;
	
	@Column(name="fecha_pedido")
	private LocalDate fechaPedido;
	
	@Column(name="idusuario")
	private Long idUsuario;

	public Long getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(Long idpedido) {
		this.idpedido = idpedido;
	}

	public String getDireccionPedido() {
		return direccionPedido;
	}

	public void setDireccionPedido(String direccionPedido) {
		this.direccionPedido = direccionPedido;
	}

	public LocalDate getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Pedido(Long idpedido, String direccionPedido, LocalDate fechaPedido, Long idUsuario) {
		super();
		this.idpedido = idpedido;
		this.direccionPedido = direccionPedido;
		this.fechaPedido = fechaPedido;
		this.idUsuario = idUsuario;
	}

	public Pedido() {
		super();
	}
	
	
}
