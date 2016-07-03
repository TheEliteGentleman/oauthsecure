/**
 * 
 */
package za.co.sindi.oauth.client.exception;

import za.co.sindi.oauth.client.parameters.oauth2.OAuth2ErrorParameters;

/**
 * @author Bienfait Sindi
 * @since 02 October 2014
 *
 */
public class OAuth2ErrorResponseException extends OAuthResponseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4073148698902193511L;
	
	private int statusCode = -1;
	private OAuth2ErrorParameters errorParameters;

	/**
	 * @param errorParameters
	 */
	public OAuth2ErrorResponseException(OAuth2ErrorParameters errorParameters) {
		super();
		this.errorParameters = errorParameters;
	}

	/**
	 * @param statusCode
	 * @param errorParameters
	 */
	public OAuth2ErrorResponseException(int statusCode,	OAuth2ErrorParameters errorParameters) {
		super();
		this.statusCode = statusCode;
		this.errorParameters = errorParameters;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @return the errorParameters
	 */
	public OAuth2ErrorParameters getErrorParameters() {
		return errorParameters;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OAuth2ErrorResponseException [statusCode=" + statusCode + ", errorParameters=" + errorParameters.toMap() + "]";
	}
}
