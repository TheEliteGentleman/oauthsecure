/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.auth;

import za.co.sindi.oauth.client.credentials.oauth2.AccessToken;
import za.co.sindi.oauth.client.transport.HttpAuthorization;
import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.http.HttpRequest;

/**
 * @author Buhake Sindi
 * @since 13 March 2012
 *
 */
public class OAuth2BearerAuthentication extends HttpAuthorization {

	private String accessToken;
	
	/**
	 * @param accessToken
	 */
	public OAuth2BearerAuthentication(AccessToken accessToken) {
		super();
		
		if (accessToken == null) {
			throw new IllegalArgumentException("Access token may not be null.");
		}
		
		if (!accessToken.getTokenType().equalsIgnoreCase(getSchemeName())) {
			throw new IllegalArgumentException("Access token contains invalid token type '" + accessToken.getTokenType() + "' (expected: " + getSchemeName() + ")");
		}
		
		this.accessToken = accessToken.getAccessToken();
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.auth.OAuthHttpAuthorization#getSchemeName()
	 */
	@Override
	public String getSchemeName() {
		// TODO Auto-generated method stub
		return "Bearer";
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.auth.OAuthHttpAuthorization#createAuthorizationString(net.oauth.transport.http.HttpRequest)
	 */
	@Override
	protected String createAuthorizationString(HttpRequest request)	throws AuthorizationException {
		// TODO Auto-generated method stub
		return accessToken;
	}
}
