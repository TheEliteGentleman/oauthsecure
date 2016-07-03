/**
 * 
 */
package za.co.sindi.oauth.server.exception;

/**
 * @author Bienfait Sindi
 * @since 20 October 2014
 *
 */
public class ParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 418355638290979255L;

//	/**
//	 * 
//	 */
//	public ParseException() {
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * @param message
	 */
	public ParseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ParseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ParseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

//	/**
//	 * @param message
//	 * @param cause
//	 * @param enableSuppression
//	 * @param writableStackTrace
//	 */
//	public ParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//		// TODO Auto-generated constructor stub
//	}
}
