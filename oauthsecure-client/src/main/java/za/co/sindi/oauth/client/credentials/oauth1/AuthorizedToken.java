/**
 * 
 */
package za.co.sindi.oauth.client.credentials.oauth1;

/**
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public class AuthorizedToken extends TokenBasedCredentials {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4419794775018473437L;
	private String verifier;

	/**
	 * @return the verifier
	 */
	public String getVerifier() {
		return verifier;
	}

	/**
	 * @param verifier the verifier to set
	 */
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
}
