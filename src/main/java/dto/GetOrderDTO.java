package dto;

import java.util.List;

public class GetOrderDTO {

	private Long id;

	private String orderAddress;

	private String userName;

	private List<GetOrderDetailsDTO> OrderDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<GetOrderDetailsDTO> getOrderDetails() {
		return OrderDetails;
	}

	public void setOrderDetails(List<GetOrderDetailsDTO> orderDetails) {
		OrderDetails = orderDetails;
	}

	public GetOrderDTO(Long id, String orderAddress, String userName, List<GetOrderDetailsDTO> orderDetails) {
		super();
		this.id = id;
		this.orderAddress = orderAddress;
		this.userName = userName;
		OrderDetails = orderDetails;
	}

	public GetOrderDTO() {
		super();
	}

}
