/**
 * 
 */
package za.co.sindi.oauth.client.request.oauth2;

import za.co.sindi.oauth.client.ResponseHandler;
import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.transport.HttpAuthorization;
import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.http.HttpRequest;

/**
 * @author Bienfait Sindi
 * @since 07 January 2016
 *
 */
public class OAuth2ProtectedResourceRequest extends OAuth2Request {

	private HttpAuthorization authorization;
	
	/**
	 * @param requestMethod
	 * @param requestUrl
	 * @param responseHandler
	 */
	public OAuth2ProtectedResourceRequest(String requestMethod, String requestUrl, ResponseHandler<?> responseHandler) {
		super(requestMethod, requestUrl, responseHandler);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param requestMethod
	 * @param requestUrl
	 */
	public OAuth2ProtectedResourceRequest(String requestMethod, String requestUrl) {
		super(requestMethod, requestUrl);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param authorization the authorization to set
	 */
	public OAuth2ProtectedResourceRequest setAuthorization(HttpAuthorization authorization) {
		this.authorization = authorization;
		return this;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.request.oauth2.OAuth2Request#authenticateHttpRequest(za.co.sindi.oauth.client.transport.http.HttpRequest)
	 */
	@Override
	protected void authenticateHttpRequest(HttpRequest request) throws OAuthRequestException, AuthorizationException {
		// TODO Auto-generated method stub
		if (authorization == null) {
			throw new OAuthRequestException("An HTTP OAuth authorization is required (set it via the setAuthorization() method).");
		}
		
		//Authenticate
		authorization.authenticate(request);
	}
}
