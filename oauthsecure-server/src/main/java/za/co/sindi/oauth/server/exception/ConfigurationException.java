/**
 * 
 */
package za.co.sindi.oauth.server.exception;

/**
 * @author Buhake Sindi
 * @since 12 April 2012
 *
 */
public class ConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -803606946048888290L;

	/**
	 * @param message
	 */
	public ConfigurationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ConfigurationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
