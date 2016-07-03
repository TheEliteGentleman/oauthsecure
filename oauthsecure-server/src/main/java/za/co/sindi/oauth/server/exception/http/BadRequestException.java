/**
 * 
 */
package za.co.sindi.oauth.server.exception.http;

import za.co.sindi.oauth.server.exception.HttpException;

/**
 * @author Bienfait Sindi
 * @since 08 Feburary 2016
 *
 */
public class BadRequestException extends HttpException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8610874878874146938L;

	/**
	 * 
	 */
	public BadRequestException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public BadRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
