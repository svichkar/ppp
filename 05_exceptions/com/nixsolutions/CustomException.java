package com.nixsolutions;

public class CustomException extends RuntimeException {
	
	private static final long serialVersionUID = 123124215L;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(Throwable t) {
		super(t);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
