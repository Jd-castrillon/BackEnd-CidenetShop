package dto;

public class GetPictureDTO {
	
	private Long productId;
	
	private byte[] picture;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public GetPictureDTO(Long productId, byte[] picture) {
		super();
		this.productId = productId;
		this.picture = picture;
	}

	public GetPictureDTO() {
		super();
	}
	
	
	
	
}
