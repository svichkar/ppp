package com.nixsolutions.error;

public class CustomDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomDataException() {

	}

	public CustomDataException(String message) {
		super(message);
	}

	public CustomDataException(Throwable cause) {
		super(cause);
	}

	public CustomDataException(String message, Throwable cause) {
		super(message, cause);
	}
}
