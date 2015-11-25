package exception;

import java.io.IOException;

/**
 * The Class MyOwnExeption.
 */
public class MyOwnExeption extends RuntimeException {

	/**
	 * Instantiates a new my own exception.
	 */
	public MyOwnExeption() {
		System.out.println("Problems related to creating file or writing in the file!");
	}

	/**
	 * Instantiates a new my own exception.
	 *
	 * @param message
	 *            for information about exception
	 */
	public MyOwnExeption(String message) throws IOException{
		super(message);
		System.out.println(message);
	}
	public MyOwnExeption(String message, Throwable t) throws IOException {
		super(message,t);
		System.out.println(message);
	}
}
