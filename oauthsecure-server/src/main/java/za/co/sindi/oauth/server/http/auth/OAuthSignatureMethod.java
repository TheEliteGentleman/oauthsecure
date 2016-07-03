/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * @author Bienfait Sindi
 * @since 09 March 2016
 *
 */
public enum OAuthSignatureMethod {
	HMAC_SHA1(OAuth1Constants.SIGNATURE_HMAC_SHA1)
	,RSA_SHA1(OAuth1Constants.SIGNATURE_RSA_SHA1)
	,PLAINTEXT(OAuth1Constants.SIGNATURE_PLAINTEXT)
	;
	private final String method;

	/**
	 * @param method
	 */
	private OAuthSignatureMethod(String method) {
		this.method = method;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return method;
	}
	
	public static OAuthSignatureMethod of(final String value) {
		if (HMAC_SHA1.method.equals(value)) {
			return HMAC_SHA1;
		}
		
		if (RSA_SHA1.method.equals(value)) {
			return RSA_SHA1;
		}
		
		if (PLAINTEXT.method.equals(value)) {
			return PLAINTEXT;
		}
		
		throw new IllegalArgumentException("Invalid " + OAuth1Constants.OAUTH_SIGNATURE_METHOD + " '" + value + "'.");
	}
}
