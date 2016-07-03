/**
 * 
 */
package za.co.sindi.oauth.server.exception;

/**
 * @author Bienfait Sindi
 * @since 04 February 2016
 *
 */
public abstract class OAuthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6897028570079748806L;

	/**
	 * @param message
	 * @param cause
	 */
	protected OAuthException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	protected OAuthException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	protected OAuthException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
