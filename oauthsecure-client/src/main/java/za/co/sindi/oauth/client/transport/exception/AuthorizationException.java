/**
 * 
 */
package za.co.sindi.oauth.client.transport.exception;

/**
 * @author Bienfait Sindi
 * @since 08 March 2016
 *
 */
public class AuthorizationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2488016833528912531L;

//	/**
//	 * 
//	 */
//	public AuthorizationException() {
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * @param message
	 */
	public AuthorizationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public AuthorizationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}
