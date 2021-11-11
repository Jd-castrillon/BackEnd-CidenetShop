package dto;

public class GetOrderDetailsDTO {

	private Long idProduct;
	private Integer quantity;
	private Double salePrice;

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public GetOrderDetailsDTO(Long idProduct, Integer quantity, Double salePrice) {
		super();
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.salePrice = salePrice;
	}

	public GetOrderDetailsDTO() {
		super();
	}

}
