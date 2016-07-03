/**
 * 
 */
package za.co.sindi.oauth.client.credentials.oauth2;

import za.co.sindi.oauth.client.credentials.OAuthCredentials;

/**
 * @author Buhake Sindi
 * @since 14 February 2012
 *
 */
public abstract class OAuth2Credentials implements OAuthCredentials {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4975843318975667357L;
	private int expiresIn;
	private String scope;
	private String state;
	
	/**
	 * @return the expiresIn
	 */
	public int getExpiresIn() {
		return expiresIn;
	}

	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.Credentials#getOAuthVersion()
	 */
	@Override
	public final int getOAuthVersion() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public abstract int getDraft();
}
