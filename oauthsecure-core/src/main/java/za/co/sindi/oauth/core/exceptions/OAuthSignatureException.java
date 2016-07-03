/**
 * 
 */
package za.co.sindi.oauth.core.exceptions;


/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public class OAuthSignatureException extends OAuthException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2874755119372354073L;

	/**
	 * 
	 */
	public OAuthSignatureException() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @param message
	 */
	public OAuthSignatureException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public OAuthSignatureException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OAuthSignatureException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
