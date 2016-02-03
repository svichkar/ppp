package com.nixsolutions;

public class FileAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileAlreadyExistsException() {
		super();
	}

	public FileAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileAlreadyExistsException(Throwable cause) {
		super(cause);
	}

	public FileAlreadyExistsException(String message) {
		super(message);
	}

}
