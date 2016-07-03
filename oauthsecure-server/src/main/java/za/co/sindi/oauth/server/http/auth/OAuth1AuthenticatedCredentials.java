/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import java.io.Serializable;

import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * @author Bienfait Sindi
 * @since 18 January 2016
 *
 */
public class OAuth1AuthenticatedCredentials extends AbstractOAuth1Credentials implements OAuth1Credentials, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -145451460898121410L;
	
	private String token;

	/**
	 * @param consumerKey
	 * @param signatureMethod
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @param version
	 * @param token
	 */
	public OAuth1AuthenticatedCredentials(String consumerKey, OAuthSignatureMethod signatureMethod, Long timestamp, String nonce, String signature, String version, String token) {
		super(consumerKey, signatureMethod, timestamp, nonce, signature, version);
		if (token == null || token.isEmpty()) {
			throw new IllegalArgumentException("No " + OAuth1Constants.OAUTH_TOKEN + " was specified.");
		}
		
		this.token = token;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
}
