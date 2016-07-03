/**
 * 
 */
package za.co.sindi.oauth.client.credentials.oauth1;

/**
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public abstract class TokenSecretBasedCredentials extends TokenBasedCredentials {

	/**
	 * 
	 */
	private static final long serialVersionUID = -95805925369470465L;
	private String tokenSecret;

	/**
	 * @return the tokenSecret
	 */
	public String getTokenSecret() {
		return tokenSecret;
	}

	/**
	 * @param tokenSecret the tokenSecret to set
	 */
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
}
