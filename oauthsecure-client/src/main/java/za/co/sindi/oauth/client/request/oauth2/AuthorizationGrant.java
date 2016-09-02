/**
 * 
 */
package za.co.sindi.oauth.client.request.oauth2;

import za.co.sindi.oauth.client.parameters.oauth2.OAuth2Parameters;
import za.co.sindi.oauth.core.utils.OAuth2Constants;
import za.co.sindi.oauth.util.OAuth2Util;

/**
 * @author Buhake Sindi
 * @since 07 March 2012
 *
 */
public final class AuthorizationGrant {

	public static final String AUTHORIZATION_CODE = "authorization_code";
	public static final String PASSWORD = "password";
	public static final String ASSERTION = "assertion";
	public static final String REFRESH_TOKEN = "refresh_token";
	public static final String NONE = "none";
	public static final String CLIENT_CREDENTIALS = "client_credentials";
	
	private OAuth2Parameters parameters;

	/**
	 * @param parameters
	 */
	private AuthorizationGrant(OAuth2Parameters parameters) {
		super();
		
		if (parameters == null) {
			throw new IllegalArgumentException("OAuth 2 parameters cannot be null.");
		}
		
		if (!parameters.containsKey(OAuth2Constants.GRANT_TYPE)) {
			throw new IllegalArgumentException(OAuth2Constants.GRANT_TYPE + " is required.");
		}
		
		this.parameters = parameters;
	}

	/**
	 * @return the parameters
	 */
	public OAuth2Parameters getParameters() {
		return parameters;
	}
	
	/**
	 * @param clientId the clientId to set
	 */
	public AuthorizationGrant setClientId(String clientId) {
		if (clientId == null) {
			parameters.remove(OAuth2Constants.CLIENT_ID);
		} else {
			parameters.setClientId(clientId);
		}
		return this;
	}

	/**
	 * @param clientSecret the clientSecret to set
	 */
	public AuthorizationGrant setClientSecret(String clientSecret) {
		if (clientSecret == null) {
			parameters.remove(OAuth2Constants.CLIENT_SECRET);
		} else {
			parameters.setClientSecret(clientSecret);
		}
		return this;
	}

	public static AuthorizationGrant authorizationCode(String code) {
		return authorizationCode(code, null);
	}
	
	public static AuthorizationGrant authorizationCode(String code, String redirectUri) {
		if (code == null || code.isEmpty()) {
			throw new IllegalArgumentException(OAuth2Constants.CODE + " is required.");
		}
		
//		if (redirectUri == null || redirectUri.isEmpty()) {
//			throw new IllegalArgumentException(OAuth2Constants.REDIRECT_URI + " is required.");
//		}
		
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setGrantType(AUTHORIZATION_CODE);
		parameters.setCode(code);
		if (redirectUri != null && !redirectUri.isEmpty()) {
			parameters.setRedirectUri(OAuth2Util.normalizeUrl(redirectUri));
		}
		return new AuthorizationGrant(parameters);
	}
	
	public static AuthorizationGrant clientCredentials() {
		return clientCredentials(null);
	}
	
	public static AuthorizationGrant clientCredentials(String scope) {
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setGrantType(CLIENT_CREDENTIALS);
		if (scope != null && !scope.isEmpty()) {
			parameters.setScope(scope);
		}
		
		return new AuthorizationGrant(parameters);
	}
	
	public static AuthorizationGrant password(String username, String password) {
		return password(username, password, null);
	}
	
	public static AuthorizationGrant password(String username, String password, String scope) {
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException(OAuth2Constants.USERNAME + " is required.");
		}
		
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException(OAuth2Constants.PASSWORD + " is required.");
		}
		
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setGrantType(PASSWORD);
		parameters.setUserName(username);
		parameters.setPassword(password);
		if (scope != null && !scope.isEmpty()) {
			parameters.setScope(scope);
		}
		return new AuthorizationGrant(parameters);
	}
	
	public static AuthorizationGrant refreshToken(String refreshToken) {
		return refreshToken(refreshToken, null);
	}
	
	public static AuthorizationGrant refreshToken(String refreshToken, String scope) {
		if (refreshToken == null || refreshToken.isEmpty()) {
			throw new IllegalArgumentException(OAuth2Constants.REFRESH_TOKEN + " is required.");
		}
		
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setGrantType(REFRESH_TOKEN);
		parameters.setUserName(refreshToken);
		if (scope != null && !scope.isEmpty()) {
			parameters.setPassword(scope);
		}
		
		return new AuthorizationGrant(parameters);
	}
}
