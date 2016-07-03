/**
 * 
 */
package za.co.sindi.oauth.core.signers;


/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public abstract class OAuthTokenSecretSigner implements OAuthSigner {

	private String consumerSecret;
	private String tokenSecret;

	/**
	 * @param consumerSecret
	 * @param tokenSecret
	 */
	protected OAuthTokenSecretSigner(String consumerSecret, String tokenSecret) {
		super();
		this.consumerSecret = consumerSecret;
		this.tokenSecret = tokenSecret;
	}

	/**
	 * @return the consumerSecret
	 */
	protected String getConsumerSecret() {
		return consumerSecret;
	}

	/**
	 * @return the tokenSecret
	 */
	protected String getTokenSecret() {
		return tokenSecret;
	}
}
