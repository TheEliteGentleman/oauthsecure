/**
 * 
 */
package za.co.sindi.oauth.client.credentials.oauth1;

import za.co.sindi.oauth.client.credentials.OAuthCredentials;

/**
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public abstract class OAuth1Credentials implements OAuthCredentials {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6722262760648627377L;

	/* (non-Javadoc)
	 * @see net.oauth.core.Credentials#getOAuthVersion()
	 */
	@Override
	public int getOAuthVersion() {
		// TODO Auto-generated method stub
		return 1;
	}
}
