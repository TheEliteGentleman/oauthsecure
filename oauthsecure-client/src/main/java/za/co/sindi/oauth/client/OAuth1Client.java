/**
 * 
 */
package za.co.sindi.oauth.client;


import za.co.sindi.oauth.client.request.oauth1.OAuth1AuthorizationUrl;
import za.co.sindi.oauth.client.request.oauth1.OAuth1Request;
import za.co.sindi.oauth.client.request.oauth1.enums.RequestType;
import za.co.sindi.oauth.client.response.oauth1.AccessTokenResponseHandler;
import za.co.sindi.oauth.client.response.oauth1.RequestTokenResponseHandler;
import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * @author Buhake Sindi
 * @since 02 February 2012
 *
 */
public class OAuth1Client extends AbstractOAuthClient<OAuth1Endpoints> {
	
	/**
	 * @param endpoints
	 */
	public OAuth1Client(OAuth1Endpoints endpoints) {
		super(endpoints);
	}

	public OAuth1Request getRequestTokenRequest() {
		// TODO Auto-generated method stub
		return new OAuth1Request(RequestType.REQUEST_TOKEN, HttpConstants.METHOD_POST, getEndpoints().getRequestTokenUrl(), new RequestTokenResponseHandler());
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthClient#getAuthorizationUrl()
	 */
	@Override
	public OAuth1AuthorizationUrl getAuthorizationUrl() {
		// TODO Auto-generated method stub
		return new OAuth1AuthorizationUrl(getEndpoints().getAuthorizationUrl());
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthClient#getOAuthCredentialsRequest()
	 */
	@Override
	public OAuth1Request getAccessTokenRequest() {
		// TODO Auto-generated method stub
		return new OAuth1Request(RequestType.ACCESS_TOKEN, HttpConstants.METHOD_POST, getEndpoints().getAccessTokenUrl(), new AccessTokenResponseHandler());
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.OAuthClient#getProtectedResourceRequest(java.lang.String, java.lang.String)
	 */
	@Override
	public OAuth1Request getProtectedResourceRequest(String requestMethod, String requestUrl) {
		// TODO Auto-generated method stub
		return new OAuth1Request(RequestType.PROTECTED_RESOURCE, requestMethod, requestUrl);
	}
}
