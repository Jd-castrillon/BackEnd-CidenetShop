package com.cidenetshop.model.dto;

public class NewExistingQuantityDTO {

	private Long idProduct;

	private String shortText;

	private Integer quantity;

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getShortText() {
		return shortText;
	}

	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public NewExistingQuantityDTO(Long idProduct, String shortText, Integer quantity) {
		super();
		this.idProduct = idProduct;
		this.shortText = shortText;
		this.quantity = quantity;
	}

	public NewExistingQuantityDTO() {
		super();
	}

}
