/**
 * 
 */
package za.co.sindi.oauth.client.credentials.oauth1;

import za.co.sindi.oauth.client.credentials.OAuthClientCredentials;
import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * @author Buhake Sindi
 * @since 15 February 2012
 *
 */
public class OAuth1ClientCredentials implements OAuthClientCredentials {

	/**
	 * 
	 */
	private static final long serialVersionUID = 653450556212608577L;
	private String consumerKey;
	private String consumerSecret;
	
	/**
	 * @param consumerKey
	 * @param consumerSecret
	 */
	public OAuth1ClientCredentials(String consumerKey, String consumerSecret) {
		super();
		
		if (consumerKey == null || consumerKey.isEmpty()) {
			throw new IllegalArgumentException(OAuth1Constants.OAUTH_CONSUMER_KEY + " cannot be null or blank.");
		}
		
		if (consumerSecret == null || consumerSecret.isEmpty()) {
			throw new IllegalArgumentException("oauth_consumer_secret cannot be null or blank.");
		}
		
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.OAuthCredentials#getOAuthVersion()
	 */
	@Override
	public int getOAuthVersion() {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * @return the consumerKey
	 */
	public String getConsumerKey() {
		return consumerKey;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.OAuthClientCredentials#getSecret()
	 */
	@Override
	public String getSecret() {
		// TODO Auto-generated method stub
		return consumerSecret;
	}
}
