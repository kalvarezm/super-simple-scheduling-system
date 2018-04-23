package com.thinknear.service.exception;

@SuppressWarnings("serial")
public class CustomNotFoundException extends Exception {

	private static final String DEFAULT_MESSAGE = "Not Found";
	private String message;

	public CustomNotFoundException() {
		super(DEFAULT_MESSAGE);
		this.message = DEFAULT_MESSAGE;
	}

	public CustomNotFoundException(String message) {
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
