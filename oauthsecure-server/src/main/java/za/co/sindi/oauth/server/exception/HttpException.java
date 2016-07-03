/**
 * 
 */
package za.co.sindi.oauth.server.exception;

/**
 * @author Bienfait Sindi
 * @since 23 January 2016
 *
 */
public abstract class HttpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -339231762548938280L;

	/**
	 * 
	 */
	protected HttpException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	protected HttpException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	protected HttpException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	protected HttpException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
