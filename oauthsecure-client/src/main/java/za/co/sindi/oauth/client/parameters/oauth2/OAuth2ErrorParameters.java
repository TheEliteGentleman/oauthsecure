/**
 * 
 */
package za.co.sindi.oauth.client.parameters.oauth2;

import za.co.sindi.oauth.client.parameters.OAuthParameters;
import za.co.sindi.oauth.core.enums.OAuth2Error;
import za.co.sindi.oauth.core.utils.OAuth2Constants;

/**
 * @author Buhake Sindi
 * @since 21 February 2012
 *
 */
public class OAuth2ErrorParameters extends OAuthParameters {

	private OAuth2Error error;
	
	/**
	 * 
	 */
	public OAuth2ErrorParameters() {
		// TODO Auto-generated constructor stub
		this(null);
	}
	
	/**
	 * 
	 * @param error
	 */
	public OAuth2ErrorParameters(OAuth2Error error) {
		// TODO Auto-generated constructor stub
		super();
		setError(error);
	}
	
	/**
	 * @return the error
	 */
	public OAuth2Error getError() {
		return error;
	}
	
	/**
	 * @param error the error to set
	 */
	public void setError(OAuth2Error error) {
		this.error = error;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return get(OAuth2Constants.ERROR_DESCRIPTION);
	}

	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		put(OAuth2Constants.ERROR_DESCRIPTION, errorDescription);
	}

	/**
	 * @return the errorUri
	 */
	public String getErrorUri() {
		return get(OAuth2Constants.ERROR_URI);
	}

	/**
	 * @param errorUri the errorUri to set
	 */
	public void setErrorUri(String errorUri) {
		put(OAuth2Constants.ERROR_URI, errorUri);
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return get(OAuth2Constants.STATE);
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		put(OAuth2Constants.STATE, state);
	}
}
