package com.nixsolutions;

public class CustomException extends Exception {
	
	private static final long serialVersionUID = 123124215L;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
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
