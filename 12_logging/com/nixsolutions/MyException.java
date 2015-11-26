package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyException extends RuntimeException {
	private static final Logger LOG = LogManager.getLogger();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException(String message, Throwable cause) {
		super(message, cause);
		LOG.error("Error caused by {} with message: {}", cause.getClass().getName(), message);
	}
	
	public MyException(String message) {
		super(message);
		LOG.error("Error caused with message: {}", message);
	}
}
