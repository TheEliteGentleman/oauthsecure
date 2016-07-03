/**
 * 
 */
package za.co.sindi.oauth.server.context;

import za.co.sindi.oauth.server.config.element.OAuthConfig;


/**
 * @author Bienfait Sindi
 * @since 30 October 2014
 *
 */
public interface OAuthContext extends Context {

	public OAuthConfig getOAuthConfig();
	public ContainerContext getContainer();
	
}
