/**
 * 
 */
package za.co.sindi.oauth.server.service;

import za.co.sindi.oauth.server.context.RequestContext;
import za.co.sindi.oauth.server.context.ResponseContext;
import za.co.sindi.oauth.server.exception.ServiceException;
import za.co.sindi.oauth.server.result.Result;

/**
 * @author Buhake Sindi
 * @since 09 April 2012
 *
 */
public interface Service {

	public Result service(final RequestContext request, final ResponseContext response) throws ServiceException;
}
