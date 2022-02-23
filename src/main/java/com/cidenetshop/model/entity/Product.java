package com.cidenetshop.model.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	private static final String ID_SEQUENCE_GENERATOR_NAME = "products_id_sequence_generator";
	private static final String ID_SEQUENCE_NAME = "products_id_sequence";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE_GENERATOR_NAME)
	@SequenceGenerator(name = ID_SEQUENCE_GENERATOR_NAME, sequenceName = ID_SEQUENCE_NAME, allocationSize = 1)
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

	@Column(name = "brand")
	private String brand;

	@Column(name = "searches")
	private Long searches;

	@ManyToOne
	@JoinColumn(name = "id_gender")
	private Gender gender;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ExistingQuantity> existingQuantity;

	@OneToMany(mappedBy = "product", cascade = { CascadeType.ALL })
	private List<OrderDetail> orderDetails;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Picture picture;

	public Product(Long id, String name, String description, String color, Double price, String brand, Gender gender,
			Set<ExistingQuantity> existingQuantity, Picture picture) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
		this.price = price;
		this.brand = brand;
		this.searches = (long) 0;
		this.gender = gender;
		this.existingQuantity = existingQuantity;
		this.picture = picture;
	}

	public Product() {
		super();
	}

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBrand() {
		return brand;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Long getSearches() {
		return searches;
	}

	public void setSearches(Long searches) {
		this.searches = searches;
	}

	public static String getIdSequenceGeneratorName() {
		return ID_SEQUENCE_GENERATOR_NAME;
	}

	public static String getIdSequenceName() {
		return ID_SEQUENCE_NAME;
	}

	public Set<ExistingQuantity> getExistingQuantity() {
		return existingQuantity;
	}

	public void setExistingQuantity(Set<ExistingQuantity> existingQuantity) {
		this.existingQuantity = existingQuantity;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
