package dto;

import java.util.List;

public class GetProductDTO {

	private Long id;

	private String name;

	private String description;

	private String color;

	private Double price;

	private String productType;

	private List<pepe> pepe;

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

	public List<pepe> getPepe() {
		return pepe;
	}

	public void setPepe(List<pepe> pepe) {
		this.pepe = pepe;
	}

	public GetProductDTO(Long id, String name, String description, String color, Double price, String productType,
			List<dto.pepe> pepe) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.color = color;
		this.price = price;
		this.productType = productType;
		this.pepe = pepe;
	}

	public GetProductDTO() {
		super();
	}
	
	

}
