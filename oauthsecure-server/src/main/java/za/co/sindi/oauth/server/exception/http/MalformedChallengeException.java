/**
 * 
 */
package za.co.sindi.oauth.server.exception.http;

import za.co.sindi.oauth.server.exception.HttpException;

/**
 * @author Bienfait Sindi
 * @since 08 January 2016
 *
 */
public class MalformedChallengeException extends HttpException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2453861254369225693L;

	/**
	 * 
	 */
	public MalformedChallengeException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public MalformedChallengeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public MalformedChallengeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MalformedChallengeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

//	/**
//	 * @param message
//	 * @param cause
//	 * @param enableSuppression
//	 * @param writableStackTrace
//	 */
//	public ChallengeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//		// TODO Auto-generated constructor stub
//	}
}
