/**
 * 
 */
package za.co.sindi.oauth.client.exception;

import za.co.sindi.oauth.core.exceptions.OAuthException;

/**
 * @author Buhake Sindi
 * @since 07 February 2012
 *
 */
public class OAuthResponseException extends OAuthException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3420213709488747064L;

	/**
	 * 
	 */
	public OAuthResponseException() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @param message
	 */
	public OAuthResponseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public OAuthResponseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OAuthResponseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
