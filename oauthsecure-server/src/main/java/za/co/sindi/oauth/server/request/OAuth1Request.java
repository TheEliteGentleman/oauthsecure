/**
 * 
 */
package za.co.sindi.oauth.server.request;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.context.RequestContext;
import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.http.auth.OAuth1Credentials;

/**
 * @author Bienfait Sindi
 * @since 16 Febuary 2016
 *
 */
public class OAuth1Request extends AbstractOAuthRequest implements OAuthRequest {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.request.AbstractOAuthRequest#doExecute(za.co.sindi.oauth.server.context.RequestContext)
	 */
	@Override
	protected void doExecute(RequestContext request) {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(request instanceof HttpRequestContext, "Request is not an incoming HTTP request.");
		HttpRequestContext httpRequest = (HttpRequestContext) request;
		OAuth1Credentials credentials = null;
		
	}
}
