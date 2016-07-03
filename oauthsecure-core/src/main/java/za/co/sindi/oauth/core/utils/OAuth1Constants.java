/**
 * 
 */
package za.co.sindi.oauth.core.utils;

/**
 * @author Buhake Sindi
 * @since 27 January 2012
 *
 */
public final class OAuth1Constants {

	private OAuth1Constants() {
		throw new AssertionError("Private Constructor.");
	}
	
	public static final String OAUTH_BODY_HASH="oauth_body_hash";
	public static final String OAUTH_CALLBACK = "oauth_callback";
	public static final String OAUTH_CALLBACK_CONFIRMED = "oauth_callback_confirmed";
	public static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
	public static final String OAUTH_NONCE = "oauth_nonce";
	public static final String OAUTH_TOKEN = "oauth_token";
	public static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
	public static final String OAUTH_SIGNATURE = "oauth_signature";
	public static final String OAUTH_TIMESTAMP = "oauth_timestamp";
	public static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";
	public static final String OAUTH_VERIFIER = "oauth_verifier";
	public static final String OAUTH_VERSION = "oauth_version";
	public static final String OOB = "oob";
	public static final String VERSION_1_0 = "1.0";
	
	public static final String SIGNATURE_HMAC_SHA1 = "HMAC-SHA1";
	public static final String SIGNATURE_RSA_SHA1 = "RSA-SHA1";
	public static final String SIGNATURE_PLAINTEXT = "PLAINTEXT";
}
