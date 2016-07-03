/**
 * 
 */
package za.co.sindi.oauth.server.result;

import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.exception.ResultException;

/**
 * @author Buhake Sindi
 * @since 15 April 2012
 *
 */
public interface Result {

//	public void executeResult(RequestContext requestContext, ResponseContext responseContext) throws ResultException;
	public void executeResult(OAuthContext context) throws ResultException;
}
