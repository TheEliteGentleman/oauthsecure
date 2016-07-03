/**
 * 
 */
package za.co.sindi.oauth.web.service.oauth1;

import za.co.sindi.oauth.core.utils.OAuth1Constants;
import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.result.http.HttpResult;
import za.co.sindi.oauth.server.service.OAuth1Service;

/**
 * @author Buhake Sindi
 * @since 29 May 2012
 *
 */
public class OAuth1ConsumerService extends OAuth1Service {

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.service.HttpService#executeGet(com.neurologic.oauth.core.context.HttpRequestContext)
	 */
	@Override
	protected HttpResult executeGet(HttpRequestContext requestContext) {
		// TODO Auto-generated method stub
		String oauthToken = requestContext.getParameter(OAuth1Constants.OAUTH_TOKEN);
		String verifier = requestContext.getParameter(OAuth1Constants.OAUTH_VERIFIER);
		
		return super.executeGet(requestContext);
	}
}
