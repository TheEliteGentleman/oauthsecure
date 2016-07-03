/**
 * 
 */
package za.co.sindi.oauth.server.service;

import za.co.sindi.oauth.server.Path;
import za.co.sindi.oauth.server.config.element.OAuthConfig;
import za.co.sindi.oauth.server.exception.ServiceException;

/**
 * @author Bienfait Sindi
 * @since 01 January 2015
 *
 */
public interface ServiceInvocationFactory {

	public ServiceInvocation createServiceInvocation(final OAuthConfig oauthConfig, final Path path) throws ServiceException;
}
