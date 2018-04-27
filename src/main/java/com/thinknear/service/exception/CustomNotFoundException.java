package com.thinknear.service.exception;

@SuppressWarnings("serial")
public class CustomNotFoundException extends Exception {

	public static final String DEFAULT_MESSAGE = "Not Found";
	private final String message;

	public CustomNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
