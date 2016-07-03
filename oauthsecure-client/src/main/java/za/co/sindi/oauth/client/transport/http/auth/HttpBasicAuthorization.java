/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.auth;

import za.co.sindi.codec.Base64Codec;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.client.transport.HttpAuthorization;
import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.http.HttpRequest;

/**
 * @author Buhake Sindi
 * @since 11 March 2012
 *
 */
public class HttpBasicAuthorization extends HttpAuthorization {

	private final String username;
	private final String password;
	
	/**
	 * @param username
	 * @param password
	 */
	public HttpBasicAuthorization(String username, String password) {
		super();
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Username is null or empty.");
		}
		
		this.username = username;
		this.password = password == null ? "" : password;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.auth.OAuthAuthorizationScheme#getSchemeName()
	 */
	@Override
	public String getSchemeName() {
		// TODO Auto-generated method stub
		return "Basic";
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.auth.OAuthHttpAuthorizationScheme#createAuthorizationString(net.oauth.core.OAuthClientCredentials, net.oauth.transport.http.HttpRequest)
	 */
	@Override
	protected String createAuthorizationString(HttpRequest request) throws AuthorizationException {
		// TODO Auto-generated method stub
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(username).append(":").append(password);
//			return Base64Codec.encode(sb.toString(), "UTF-8");
			return Base64Codec.getBase64Codec().encode(sb.toString(), "UTF-8");
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			throw new AuthorizationException(e);
		}
	}
}
