package com.nixsolutions;

import java.io.IOException;

public class MyException extends RuntimeException {

	/**
	 * id required for serialization
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException() {
		super();
	}

	public MyException(String message) {
		super(message);
	}

	public MyException(Throwable c) {
		super(c);

	}

}
