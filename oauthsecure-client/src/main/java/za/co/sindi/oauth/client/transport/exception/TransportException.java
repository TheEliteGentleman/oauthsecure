/**
 * 
 */
package za.co.sindi.oauth.client.transport.exception;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public class TransportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8791559437379142052L;

	/**
	 * @param arg0
	 */
	public TransportException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public TransportException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TransportException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
