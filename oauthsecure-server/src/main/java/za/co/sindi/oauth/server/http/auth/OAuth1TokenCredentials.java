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
public class OAuth1TokenCredentials extends AbstractOAuth1Credentials implements OAuth1Credentials, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5619796459953017381L;
	
	private String token;
	private String verifier;

	/**
	 * @param consumerKey
	 * @param signatureMethod
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @param version
	 * @param token
	 * @param verifier
	 */
	public OAuth1TokenCredentials(String consumerKey, OAuthSignatureMethod signatureMethod, Long timestamp, String nonce, String signature, String version, String token, String verifier) {
		super(consumerKey, signatureMethod, timestamp, nonce, signature, version);
		if (token == null || token.isEmpty()) {
			throw new IllegalArgumentException("No " + OAuth1Constants.OAUTH_TOKEN + " was specified.");
		}
		if (verifier == null || verifier.isEmpty()) {
			throw new IllegalArgumentException("No " + OAuth1Constants.OAUTH_VERIFIER + " was specified.");
		}
		
		this.token = token;
		this.verifier = verifier;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the verifier
	 */
	public String getVerifier() {
		return verifier;
	}
}
