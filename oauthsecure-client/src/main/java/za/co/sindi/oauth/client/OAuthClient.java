/**
 * 
 */
package za.co.sindi.oauth.client;


/**
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public interface OAuthClient {

	public AuthorizationUrl getAuthorizationUrl();
	public OAuthRequest getAccessTokenRequest();
	public OAuthRequest getProtectedResourceRequest(String requestMethod, String requestUrl);
}
