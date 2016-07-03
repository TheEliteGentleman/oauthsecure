/**
 * 
 */
package za.co.sindi.oauth.server.exception.oauth;

import za.co.sindi.oauth.server.exception.OAuthException;

/**
 * @author Bienfait Sindi
 * @since 16 February 2016
 *
 */
public class OAuthValidationException extends OAuthException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1994701932808269168L;

	/**
	 * @param message
	 * @param cause
	 */
	public OAuthValidationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public OAuthValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public OAuthValidationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
