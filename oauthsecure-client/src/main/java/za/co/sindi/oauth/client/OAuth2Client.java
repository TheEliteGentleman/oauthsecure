/**
 * 
 */
package za.co.sindi.oauth.client;

import za.co.sindi.oauth.client.request.oauth2.OAuth2AccesTokenRequest;
import za.co.sindi.oauth.client.request.oauth2.OAuth2AuthorizationUrl;
import za.co.sindi.oauth.client.request.oauth2.OAuth2ProtectedResourceRequest;
import za.co.sindi.oauth.client.request.oauth2.enums.ResponseType;
import za.co.sindi.oauth.client.response.oauth2.AccessTokenResponseHandler;
import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public class OAuth2Client extends AbstractOAuthClient<OAuth2Endpoints> {

	/**
	 * @param endpoints
	 */
	public OAuth2Client(OAuth2Endpoints endpoints) {
		super(endpoints);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthClient#getAuthorizationUrl()
	 */
	@Override
	public OAuth2AuthorizationUrl getAuthorizationUrl() {
		// TODO Auto-generated method stub
		return new OAuth2AuthorizationUrl(getEndpoints().getAuthorizationUrl(), ResponseType.CODE);
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthClient#getAccessTokenRequest()
	 */
	@Override
	public OAuth2AccesTokenRequest getAccessTokenRequest() {
		// TODO Auto-generated method stub
		return new OAuth2AccesTokenRequest(HttpConstants.METHOD_POST, getEndpoints().getAccessTokenUrl(), new AccessTokenResponseHandler());
	}
	
	public OAuth2AuthorizationUrl getAccessTokenImplicitUrl() {
		// TODO Auto-generated method stub
		return new OAuth2AuthorizationUrl(getEndpoints().getAuthorizationUrl(), ResponseType.TOKEN);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.OAuthClient#getProtectedResourceRequest(java.lang.String, java.lang.String)
	 */
	@Override
	public OAuth2ProtectedResourceRequest getProtectedResourceRequest(String requestMethod, String requestUrl) {
		// TODO Auto-generated method stub
		return new OAuth2ProtectedResourceRequest(requestMethod, requestUrl);
	}
}
