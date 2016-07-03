/**
 * 
 */
package za.co.sindi.oauth.client.parameters.oauth1;

import za.co.sindi.oauth.client.parameters.OAuthParameters;
import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * @author Buhake Sindi
 * @since 27 January 2012
 *
 */
public class OAuth1Parameters extends OAuthParameters {
	
	/**
	 * 
	 */
	public OAuth1Parameters() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @return the bodyHash
	 */
	public String getBodyHash() {
		return get(OAuth1Constants.OAUTH_BODY_HASH);
	}

	/**
	 * @param bodyHash the bodyHash to set
	 */
	public void setBodyHash(String bodyHash) {
		put(OAuth1Constants.OAUTH_BODY_HASH, bodyHash);
	}

	/**
	 * @return the consumerKey
	 */
	public String getConsumerKey() {
		return get(OAuth1Constants.OAUTH_CONSUMER_KEY);
	}

	/**
	 * @param consumerKey the consumerKey to set
	 */
	public void setConsumerKey(String consumerKey) {
		put(OAuth1Constants.OAUTH_CONSUMER_KEY, consumerKey);
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return get(OAuth1Constants.OAUTH_TOKEN);
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		put(OAuth1Constants.OAUTH_TOKEN, token);
	}

	/**
	 * @return the signatureMethod
	 */
	public String getSignatureMethod() {
		return get(OAuth1Constants.OAUTH_SIGNATURE_METHOD);
	}

	/**
	 * @param signatureMethod the signatureMethod to set
	 */
	public void setSignatureMethod(String signatureMethod) {
		put(OAuth1Constants.OAUTH_SIGNATURE_METHOD, signatureMethod);
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return Long.parseLong(get(OAuth1Constants.OAUTH_TIMESTAMP));
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		put(OAuth1Constants.OAUTH_TIMESTAMP, String.valueOf(timestamp));
	}

	/**
	 * @return the nonce
	 */
	public String getNonce() {
		return get(OAuth1Constants.OAUTH_NONCE);
	}

	/**
	 * @param nonce the nonce to set
	 */
	public void setNonce(String nonce) {
		put(OAuth1Constants.OAUTH_NONCE, nonce);
	}

	/**
	 * @return the callback
	 */
	public String getCallback() {
		return get(OAuth1Constants.OAUTH_CALLBACK);
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(String callback) {
		put(OAuth1Constants.OAUTH_CALLBACK, callback);
	}

	/**
	 * @return the verifier
	 */
	public String getVerifier() {
		return get(OAuth1Constants.OAUTH_VERIFIER);
	}

	/**
	 * @param verifier the verifier to set
	 */
	public void setVerifier(String verifier) {
		put(OAuth1Constants.OAUTH_VERIFIER, verifier);
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return get(OAuth1Constants.OAUTH_SIGNATURE);
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		put(OAuth1Constants.OAUTH_SIGNATURE, signature);
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return get(OAuth1Constants.OAUTH_VERSION);
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		put(OAuth1Constants.OAUTH_VERSION, version);
	}
	
	/**
	 * Set the version to "1.0" as specified on RFC 5849.
	 */
	public void setVersion() {
		setVersion(OAuth1Constants.VERSION_1_0);
	}
}
