package com.nixsolutions;

public class MyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException(String message, Throwable cause) {
		super(message, cause);
	}
}
