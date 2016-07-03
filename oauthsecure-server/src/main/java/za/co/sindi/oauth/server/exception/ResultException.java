/**
 * 
 */
package za.co.sindi.oauth.server.exception;

/**
 * @author Buhake Sindi
 * @since 15 April 2012
 *
 */
public class ResultException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3914494066344610823L;

	/**
	 * @param message
	 */
	public ResultException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ResultException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ResultException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
