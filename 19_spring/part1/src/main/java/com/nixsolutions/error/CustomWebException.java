package com.nixsolutions.error;

public class CustomWebException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomWebException() {

	}

	public CustomWebException(String message) {
		super(message);
	}

	public CustomWebException(Throwable cause) {
		super(cause);
	}

	public CustomWebException(String message, Throwable cause) {
		super(message, cause);
	}
}
