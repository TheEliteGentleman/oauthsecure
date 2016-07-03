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
public class OAuth1TemporaryCredentials extends AbstractOAuth1Credentials implements OAuth1Credentials, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8784431286152503216L;
	
	private String callback;

	/**
	 * @param consumerKey
	 * @param signatureMethod
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @param version
	 * @param callback
	 */
	public OAuth1TemporaryCredentials(String consumerKey, OAuthSignatureMethod signatureMethod, Long timestamp, String nonce, String signature, String version, String callback) {
		super(consumerKey, signatureMethod, timestamp, nonce, signature, version);
		if (callback == null || callback.isEmpty()) {
			throw new IllegalArgumentException("No " + OAuth1Constants.OAUTH_CALLBACK + " was specified.");
		}
		this.callback = callback;
	}

	/**
	 * @return the callback
	 */
	public String getCallback() {
		return callback;
	}
}
