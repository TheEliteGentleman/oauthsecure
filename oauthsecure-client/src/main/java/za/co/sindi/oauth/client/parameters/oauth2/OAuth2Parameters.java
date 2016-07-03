/**
 * 
 */
package za.co.sindi.oauth.client.parameters.oauth2;

import za.co.sindi.oauth.client.parameters.OAuthParameters;
import za.co.sindi.oauth.core.utils.OAuth2Constants;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public class OAuth2Parameters extends OAuthParameters {

//	public static final String ACCESS_TOKEN = "access_token";
//	public static final String CLIENT_ID = "client_id";
//	public static final String CODE = "code";
//	public static final String REDIRECT_URI = "redirect_uri";
//	public static final String USERNAME = "username";
//	public static final String PASSWORD = "password";
	public static final String ASSERTION_TYPE = "assertion_type";
	public static final String ASSERTION = "assertion";
//	public static final String REFRESH_TOKEN = "refresh_token";
//	public static final String TOKEN_TYPE = "token_type";
//	public static final String SCOPE = "scope";
//	public static final String RESPONSE_TYPE = "response_type";
//	public static final String GRANT_TYPE = "grant_type";
//	public static final String CLIENT_SECRET = "client_secret";
//	public static final String STATE = "state";
//	public static final String EXPIRES_IN = "expires_in";
	
	/**
	 * 
	 */
	public OAuth2Parameters() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return get(OAuth2Constants.CLIENT_ID);
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		if (clientId != null)
			put(OAuth2Constants.CLIENT_ID, clientId);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return get(OAuth2Constants.CODE);
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		if (code != null)
			put(OAuth2Constants.CODE, code);
	}

	/**
	 * @return the redirectUri
	 */
	public String getRedirectUri() {
		return get(OAuth2Constants.REDIRECT_URI);
	}

	/**
	 * @param redirectUri the redirectUri to set
	 */
	public void setRedirectUri(String redirectUri) {
		if (redirectUri != null)
			put(OAuth2Constants.REDIRECT_URI, redirectUri);
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return get(OAuth2Constants.USERNAME);
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		if (userName != null)
			put(OAuth2Constants.USERNAME, userName);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return get(OAuth2Constants.PASSWORD);
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		if (password != null)
			put(OAuth2Constants.PASSWORD, password);
	}

	/**
	 * @return the assertionType
	 */
	public String getAssertionType() {
		return get(ASSERTION_TYPE);
	}

	/**
	 * @param assertionType the assertionType to set
	 */
	public void setAssertionType(String assertionType) {
		if (assertionType != null)
			put(ASSERTION_TYPE, assertionType);
	}

	/**
	 * @return the assertion
	 */
	public String getAssertion() {
		return get(ASSERTION);
	}

	/**
	 * @param assertion the assertion to set
	 */
	public void setAssertion(String assertion) {
		if (assertion != null)
			put(ASSERTION, assertion);
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return get(OAuth2Constants.REFRESH_TOKEN);
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		if (refreshToken != null)
			put(OAuth2Constants.REFRESH_TOKEN, refreshToken);
	}

	/**
	 * @return the responseType
	 */
	public String getResponseType() {
		return get(OAuth2Constants.RESPONSE_TYPE);
	}

	/**
	 * @param responseType the responseType to set
	 */
	public void setResponseType(String responseType) {
		if (responseType != null)
			put(OAuth2Constants.RESPONSE_TYPE, responseType);
	}

	/**
	 * @return the grantType
	 */
	public String getGrantType() {
		return get(OAuth2Constants.GRANT_TYPE);
	}

	/**
	 * @param grantType the grantType to set
	 */
	public void setGrantType(String grantType) {
		if (grantType != null)
			put(OAuth2Constants.GRANT_TYPE, grantType);
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return get(OAuth2Constants.ACCESS_TOKEN);
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		if (accessToken != null && !accessToken.isEmpty())
			put(OAuth2Constants.ACCESS_TOKEN, accessToken);
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return get(OAuth2Constants.CLIENT_SECRET);
	}

	/**
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		if (clientSecret != null && !clientSecret.isEmpty()) {
			put(OAuth2Constants.CLIENT_SECRET, clientSecret);
		}
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return get(OAuth2Constants.SCOPE);
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		if (scope != null && !scope.isEmpty()) {
			put(OAuth2Constants.SCOPE, scope);
		}
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return get(OAuth2Constants.STATE);
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		if (state != null && !state.isEmpty()) {
			put(OAuth2Constants.STATE, state);
		}
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return get(OAuth2Constants.TOKEN_TYPE);
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		if (tokenType != null && !tokenType.isEmpty()) {
			put(OAuth2Constants.TOKEN_TYPE, tokenType);
		}
	}

	/**
	 * @return the expiresIn
	 */
	public int getExpiresIn() {
		String s = get(OAuth2Constants.EXPIRES_IN);
		if (s == null) {
			return 0;
		}
		
		return Integer.parseInt(s);
	}

	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(int expiresIn) {
		put(OAuth2Constants.EXPIRES_IN, String.valueOf(expiresIn));
	}
}
