/**
 * 
 */
package za.co.sindi.oauth.client.exception;

import za.co.sindi.oauth.core.exceptions.OAuthException;

/**
 * @author Buhake Sindi
 * @since 02 February 2012
 *
 */
public class OAuthRequestException extends OAuthException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8776414836634982792L;

	/**
	 * 
	 */
	public OAuthRequestException() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @param message
	 */
	public OAuthRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public OAuthRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OAuthRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
