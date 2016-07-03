/**
 * 
 */
package za.co.sindi.oauth.client.credentials.oauth1;

/**
 * @author Buhake Sindi
 * @since 28 January 2012
 *
 */
public abstract class TokenBasedCredentials extends OAuth1Credentials {

	/**
	 * 
	 */
	private static final long serialVersionUID = -71220503909960233L;
	private String token;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
