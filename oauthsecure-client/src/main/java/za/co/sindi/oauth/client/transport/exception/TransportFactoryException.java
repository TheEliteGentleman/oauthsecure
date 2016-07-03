/**
 * 
 */
package za.co.sindi.oauth.client.transport.exception;

/**
 * @author Buhake Sindi
 * @since 23 February 2012
 *
 */
public class TransportFactoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5303123949277334445L;

	/**
	 * 
	 */
	public TransportFactoryException() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @param message
	 */
	public TransportFactoryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public TransportFactoryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TransportFactoryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
