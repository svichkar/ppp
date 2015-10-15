/**
 * 
 */
package main.com.nixsolutions;


/**
 * @author mixeyes
 *
 */
public class SaveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2042835560812735675L;

	public SaveException(String message) {
		super("SaveException: \""+message+"\"");
	}

}
