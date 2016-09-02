/**
 * 
 */
package za.co.sindi.oauth.client.request;

import za.co.sindi.oauth.client.ResponseHandler;
import za.co.sindi.oauth.client.transport.http.HttpRequest;
import za.co.sindi.oauth.client.transport.http.HttpResponse;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public abstract class AbstractHttpOAuthRequest extends AbstractOAuthRequest<HttpRequest, HttpResponse> {

	/**
	 * @param requestMethod
	 * @param requestUrl
	 */
	protected AbstractHttpOAuthRequest(String requestMethod, String requestUrl) {
		super(requestMethod, requestUrl);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param requestMethod
	 * @param requestUrl
	 * @param responseHandler
	 */
	protected AbstractHttpOAuthRequest(String requestMethod, String requestUrl, ResponseHandler<?> responseHandler) {
		super(requestMethod, requestUrl, responseHandler);
		// TODO Auto-generated constructor stub
	}
}
