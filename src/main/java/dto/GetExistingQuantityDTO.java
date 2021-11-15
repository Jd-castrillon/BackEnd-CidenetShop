package dto;

public class GetExistingQuantityDTO {

	private Integer idSize;

	private String sizeShortText;

	private Integer existingQuantity;

	public Integer getIdSize() {
		return idSize;
	}

	public void setIdSize(Integer idSize) {
		this.idSize = idSize;
	}

	public String getSizeShortText() {
		return sizeShortText;
	}

	public void setSizeShortText(String sizeShortText) {
		this.sizeShortText = sizeShortText;
	}

	public Integer getExistingQuantity() {
		return existingQuantity;
	}

	public void setExistingQuantity(Integer existingQuantity) {
		this.existingQuantity = existingQuantity;
	}

	public GetExistingQuantityDTO(Integer idSize, String sizeShortText, Integer existingQuantity) {
		super();
		this.idSize = idSize;
		this.sizeShortText = sizeShortText;
		this.existingQuantity = existingQuantity;
	}

	public GetExistingQuantityDTO() {
		super();
	}

}
