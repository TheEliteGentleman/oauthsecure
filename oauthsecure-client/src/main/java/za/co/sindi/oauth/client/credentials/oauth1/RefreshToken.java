/**
 * 
 */
package za.co.sindi.oauth.client.credentials.oauth1;

/**
 * The request token and credentials.
 * 
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public class RefreshToken extends TokenSecretBasedCredentials {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5698778280942068734L;
	private boolean callbackConfirmed;

	/**
	 * @return the callbackConfirmed
	 */
	public boolean isCallbackConfirmed() {
		return callbackConfirmed;
	}

	/**
	 * @param callbackConfirmed the callbackConfirmed to set
	 */
	public void setCallbackConfirmed(boolean callbackConfirmed) {
		this.callbackConfirmed = callbackConfirmed;
	}
}
