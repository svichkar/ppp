package com.nixsolutions.exceptions;

import java.io.IOException;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomException() {
		super("You are trying to save null value to file");
	}

	public CustomException(String string) {
		super(string);
	}
	public CustomException(IOException ioException) {
		super(ioException);
	}

	public CustomException(Exception ex) {
		super(ex);
		
	}
}
