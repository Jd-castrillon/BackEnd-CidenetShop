package dto;

public class pepe {

	private Integer id;

	private String size;

	private Integer stock;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public pepe(Integer id, String size, Integer stock) {
		super();
		this.id = id;
		this.size = size;
		this.stock = stock;
	}
	
	

}
