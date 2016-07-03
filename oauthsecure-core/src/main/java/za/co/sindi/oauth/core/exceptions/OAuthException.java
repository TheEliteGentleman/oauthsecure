/**
 * 
 */
package za.co.sindi.oauth.core.exceptions;

/**
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public class OAuthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3333572441080047572L;

	/**
	 * 
	 */
	public OAuthException() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @param message
	 */
	public OAuthException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public OAuthException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OAuthException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
