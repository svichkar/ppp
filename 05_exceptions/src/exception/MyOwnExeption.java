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
	}

	/**
	 * Instantiates a new my own exception.
	 *
	 * @param message
	 *            for information about exception
	 */
	public MyOwnExeption(String message) throws IOException{
		super(message);
		System.out.println("String for saving is empty! Please enter data to string for saving in the file.");
	}
}
