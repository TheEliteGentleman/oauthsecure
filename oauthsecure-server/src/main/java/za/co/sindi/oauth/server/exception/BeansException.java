/**
 * 
 */
package za.co.sindi.oauth.server.exception;

/**
 * @author Bienfait Sindi
 * @since 20 October 2014
 *
 */
public class BeansException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4004705650279059879L;

//	/**
//	 * 
//	 */
//	public BeansException() {
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * @param message
	 */
	public BeansException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public BeansException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BeansException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

//	/**
//	 * @param message
//	 * @param cause
//	 * @param enableSuppression
//	 * @param writableStackTrace
//	 */
//	public BeansException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//		// TODO Auto-generated constructor stub
//	}
}
