/**
 * 
 */
package za.co.sindi.oauth.client.credentials.oauth2;

import za.co.sindi.oauth.client.credentials.OAuthClientCredentials;
import za.co.sindi.oauth.core.utils.OAuth2Constants;

/**
 * @author Buhake Sindi
 * @since 15 February 2012
 *
 */
public class OAuth2ClientCredentials implements OAuthClientCredentials {

	/**
	 * 
	 */
	private static final long serialVersionUID = 653450556212608577L;
	private String clientId;
	private String clientSecret;
	
	/**
	 * @param clientId
	 * @param clientSecret
	 */
	public OAuth2ClientCredentials(String clientId, String clientSecret) {
		super();
		
		if (clientId == null || clientId.isEmpty()) {
			throw new IllegalArgumentException(OAuth2Constants.CLIENT_ID + " cannot be null or blank.");
		}
		
		if (clientSecret == null || clientSecret.isEmpty()) {
			throw new IllegalArgumentException(OAuth2Constants.CLIENT_SECRET + " cannot be null or blank.");
		}
		
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.OAuthCredentials#getOAuthVersion()
	 */
	@Override
	public int getOAuthVersion() {
		// TODO Auto-generated method stub
		return 2;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.OAuthClientCredentials#getSecret()
	 */
	@Override
	public String getSecret() {
		// TODO Auto-generated method stub
		return clientSecret;
	}
}
