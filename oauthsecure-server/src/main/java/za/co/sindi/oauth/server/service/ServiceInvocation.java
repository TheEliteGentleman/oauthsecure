/**
 * 
 */
package za.co.sindi.oauth.server.service;

import za.co.sindi.oauth.server.config.element.ServiceConfig;
import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.result.Result;

/**
 * @author Bienfait Sindi
 * @since 01 January 2016
 *
 */
public interface ServiceInvocation {

//	public void destroy();
//	public void init();
	public ServiceConfig getServiceConfig();
	public Result invoke(OAuthContext context) throws Exception;
}
