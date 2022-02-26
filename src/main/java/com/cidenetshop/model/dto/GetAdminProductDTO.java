package com.cidenetshop.model.dto;

import java.util.List;

public class GetAdminProductDTO {

	private Long id;

	private String name;

	private String description;

	private String color;

	private Double price;

	private String gender;

	private String brand;

	private Integer active;

	private List<GetExistingQuantityDTO> existingQuantity;

	private byte[] picture;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public List<GetExistingQuantityDTO> getExistingQuantity() {
		return existingQuantity;
	}

	public void setExistingQuantity(List<GetExistingQuantityDTO> existingQuantity) {
		this.existingQuantity = existingQuantity;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

}
