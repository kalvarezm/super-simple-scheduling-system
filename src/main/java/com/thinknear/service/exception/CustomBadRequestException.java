package com.thinknear.service.exception;

@SuppressWarnings("serial")
public class CustomBadRequestException extends Exception {

	private String message;

	public CustomBadRequestException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
