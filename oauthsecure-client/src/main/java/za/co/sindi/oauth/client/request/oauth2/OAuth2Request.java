/**
 * 
 */
package za.co.sindi.oauth.client.request.oauth2;

import za.co.sindi.oauth.client.ResponseHandler;
import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.request.AbstractOAuthRequest;
import za.co.sindi.oauth.client.transport.Request;
import za.co.sindi.oauth.client.transport.Response;
import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.factory.TransportFactory;
import za.co.sindi.oauth.client.transport.http.HttpRequest;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public abstract class OAuth2Request extends AbstractOAuthRequest {

	/**
	 * @param requestMethod
	 * @param requestUrl
	 */
	protected OAuth2Request(String requestMethod, String requestUrl) {
		super(requestMethod, requestUrl);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param requestMethod
	 * @param requestUrl
	 * @param responseHandler
	 */
	protected OAuth2Request(String requestMethod, String requestUrl, ResponseHandler<?> responseHandler) {
		super(requestMethod, requestUrl, responseHandler);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.request.AbstractOAuthRequest#setTransportFactory(net.oauth.transport.factory.TransportFactory)
	 */
	@Override
	public void setTransportFactory(TransportFactory<? extends Request, ? extends Response> transportFactory) {
		// TODO Auto-generated method stub
		super.setTransportFactory(transportFactory);
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.request.AbstractOAuthRequest#getTransportFactory()
	 */
	@Override
	public TransportFactory<? extends Request, ? extends Response> getTransportFactory() {
		// TODO Auto-generated method stub
		return super.getTransportFactory();
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.request.AbstractOAuthRequest#authenticateRequest(net.oauth.transport.Request)
	 */
	@Override
	protected void authenticateRequest(Request request)	throws OAuthRequestException, AuthorizationException {
		// TODO Auto-generated method stub
		if (request instanceof HttpRequest) {
			authenticateHttpRequest((HttpRequest)request);
		} else {
			throw new IllegalArgumentException("Invalid request " + request.getClass().getName());
		}
	}
	
	protected abstract void authenticateHttpRequest(HttpRequest request) throws OAuthRequestException, AuthorizationException;
}
