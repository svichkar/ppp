package exception;

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
	public MyOwnExeption(String message) {
		super(message);
	}
}
