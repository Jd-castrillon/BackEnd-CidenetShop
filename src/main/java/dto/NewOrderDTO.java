package dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewOrderDTO {
	 DateTimeFormatter isoHora = DateTimeFormatter.ISO_LOCAL_TIME;

	private Long idUser;

	private String orderAddress;

	private LocalDate orderDate ;
	
	private List<GetOrderDetailDTO> orderDetails;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public List<GetOrderDetailDTO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<GetOrderDetailDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
	
	

}
