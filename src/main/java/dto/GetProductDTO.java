package dto;

import java.util.List;

public class GetProductDTO {

	private Long id;

	private String name;

	private String description;

	private String color;

	private Double price;

	private String productType;

	private String brand;

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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public GetProductDTO(Long id, String name, String description, String color, Double price, String productType,
			String brand, List<GetExistingQuantityDTO> existingQuantity, byte[] picture) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
		this.price = price;
		this.productType = productType;
		this.brand = brand;
		this.existingQuantity = existingQuantity;
		this.picture = picture;
	}

	public GetProductDTO() {
		super();
	}

}
