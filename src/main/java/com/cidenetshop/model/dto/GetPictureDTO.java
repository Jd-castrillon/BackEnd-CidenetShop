package com.cidenetshop.model.dto;

public class GetPictureDTO {
	
	
	private byte[] picture;

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public GetPictureDTO(byte[] picture) {
		super();

		this.picture = picture;
	}

	public GetPictureDTO() {
		super();
	}

}
