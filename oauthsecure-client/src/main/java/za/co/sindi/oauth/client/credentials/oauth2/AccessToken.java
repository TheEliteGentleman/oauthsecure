/**
 * 
 */
package za.co.sindi.oauth.client.credentials.oauth2;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public class AccessToken extends OAuth2Credentials {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5714988642516604027L;
	private String accessToken;
	private String tokenType;
	private String refreshToken;
	private Map<String, String> additionalParameters;
	
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the additionalParameters
	 */
	public Map<String, String> getAdditionalParameters() {
		return Collections.unmodifiableMap(additionalParameters);
	}

	/**
	 * @param additionalParameters the additionalParameters to set
	 */
	public void setAdditionalParameter(String key, String value) {
		if (additionalParameters == null) {
			this.additionalParameters = new LinkedHashMap<String, String>();
		}
		
		additionalParameters.put(key, value);
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.credentials.oauth2.OAuth2Credentials#getDraft()
	 */
	@Override
	public int getDraft() {
		// TODO Auto-generated method stub
		return 23;
	}
}
