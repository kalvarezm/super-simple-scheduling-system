package com.thinknear.service.exception;

@SuppressWarnings("serial")
public class CustomBadRequestException extends Exception {

	private final String message;

	public CustomBadRequestException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
