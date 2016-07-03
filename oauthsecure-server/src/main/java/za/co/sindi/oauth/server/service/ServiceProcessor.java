/**
 * 
 */
package za.co.sindi.oauth.server.service;

import za.co.sindi.oauth.server.RequestPath;
import za.co.sindi.oauth.server.config.ServerConfig;
import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.exception.ServiceException;

/**
 * @author Bienfait Sindi
 * @since 11 October 2014
 *
 */
public interface ServiceProcessor {

	public void init(ServerConfig serverConfig);
	public void service(RequestPath requestPath, OAuthContext context) throws ServiceException;
}
