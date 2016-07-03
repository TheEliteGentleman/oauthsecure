/**
 * 
 */
package za.co.sindi.oauth.client.transport;

import org.apache.log4j.Logger;

import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.http.HttpRequest;
import za.co.sindi.oauth.client.transport.http.header.BasicHeader;
import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * @author Bienfait Sindi
 * @since 08 March 2016
 *
 */
public abstract class HttpAuthorization implements Authorization<HttpRequest> {

	protected final Logger logger = Logger.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.transport.Authorization#authenticate(za.co.sindi.oauth.client.transport.Request)
	 */
	@Override
	public void authenticate(HttpRequest request) throws AuthorizationException {
		// TODO Auto-generated method stub
		if (request == null) {
			throw new IllegalArgumentException("HttpRequest may not be null.");
		}
		
		request.addHeader(new BasicHeader(HttpConstants.HEADER_AUTHORIZATION, getSchemeName() + " " + createAuthorizationString(request)));
	}
	
	protected abstract String getSchemeName();
	protected abstract String createAuthorizationString(HttpRequest request) throws AuthorizationException;
}
