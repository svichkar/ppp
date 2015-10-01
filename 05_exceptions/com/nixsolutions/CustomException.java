package com.nixsolutions;

public class CustomException extends Exception {
	
	private static final long serialVersionUID = 123124215;
	
	public CustomException() {
		
	}
	
	public CustomException(Exception e) {
		this.setStackTrace(e.getStackTrace());
	}
}
