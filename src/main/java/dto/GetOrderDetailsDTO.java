package dto;

public class GetOrderDetailsDTO {

	private Long idProduct;
	private Long idOrder;
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

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public GetOrderDetailsDTO(Long idProduct, Long idOrder, Integer quantity, Double salePrice) {
		super();
		this.idProduct = idProduct;
		this.idOrder = idOrder;
		this.quantity = quantity;
		this.salePrice = salePrice;
	}

	public GetOrderDetailsDTO() {
		super();
	}

}
