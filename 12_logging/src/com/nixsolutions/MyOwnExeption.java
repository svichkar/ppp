package com.nixsolutions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The Class MyOwnExeption.
 */
public class MyOwnExeption extends RuntimeException {

	/**
	 * Instantiates a new my own exception.
	 */
	private static final Logger lOG = LogManager.getLogger(MyOwnExeption.class.getName());
	public MyOwnExeption() {
		
		lOG.error("Problems related to creating file or writing in the file!");
	}

	/**
	 * Instantiates a new my own exception.
	 *
	 * @param message
	 *            for information about exception
	 */
	public MyOwnExeption(String message) throws IOException{
		super(message);
		lOG.error(message);
	}
	public MyOwnExeption(String message, Throwable t) throws IOException {
		super(message,t);
		lOG.error(message);
	}
}
