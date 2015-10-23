package com.nixsolutions;


public class MyNewestException extends RuntimeException {

	/**
	 * id required for serialization
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyNewestException() {
		super();
	}

	public MyNewestException(String message) {
		super(message);
	}

	public MyNewestException(Throwable c) {
		super(c);

	}

}
