package com.cidenetshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "color")
	private String color;

	@Column(name = "price")
	private Double price;

	@Column(name = "existing_quantity")
	private Integer existingQuantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_product_type")
	private ProductType productType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_size")
	private Size size;

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

	public Integer getExistingQuantity() {
		return existingQuantity;
	}

	public void setExistingQuantity(Integer existingQuantity) {
		this.existingQuantity = existingQuantity;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Product(Long id, String name, String description, String color, Double price, Integer existingQuantity,
			ProductType productType, Size size) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
		this.price = price;
		this.existingQuantity = existingQuantity;
		this.productType = productType;
		this.size = size;
	}

	public Product() {
		super();
	}

}
