/**
 * 
 */
package za.co.sindi.oauth.core.utils;

/**
 * @author Bienfait Sindi
 * @since 02 October 2014
 *
 */
public final class OAuth2Constants {

	private OAuth2Constants() {
		throw new AssertionError("Private Constructor.");
	}
	
	public static final String ACCESS_TOKEN = "access_token";
	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String CODE = "code";
	public static final String EXPIRES_IN = "expires_in";
	public static final String ERROR = "error";
	public static final String ERROR_DESCRIPTION = "error_description";
	public static final String ERROR_URI = "error_uri";
	public static final String GRANT_TYPE = "grant_type";
	public static final String PASSWORD = "password";
	public static final String REDIRECT_URI = "redirect_uri";
	public static final String REFRESH_TOKEN = "refresh_token";
	public static final String RESPONSE_TYPE = "response_type";
	public static final String SCOPE = "scope";
	public static final String STATE = "state";
	public static final String TOKEN_TYPE = "token_type";
	public static final String USERNAME = "username";
}
