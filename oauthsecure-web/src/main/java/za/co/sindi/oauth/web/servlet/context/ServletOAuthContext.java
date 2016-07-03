/**
 * 
 */
package za.co.sindi.oauth.web.servlet.context;

import za.co.sindi.oauth.server.config.element.OAuthConfig;
import za.co.sindi.oauth.server.context.AbstractOAuthContext;
import za.co.sindi.oauth.server.context.ApplicationContext;
import za.co.sindi.oauth.server.context.AttributeContext;
import za.co.sindi.oauth.server.context.RequestContext;
import za.co.sindi.oauth.web.servlet.OAuthServlet;

/**
 * @author Bienfait Sindi
 * @since 21 November 2014
 *
 */
public class ServletOAuthContext extends AbstractOAuthContext {

	/**
	 * @param container
	 */
	public ServletOAuthContext(ServletContainerContext container) {
		super(container);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.OAuthContext#getOAuthConfig()
	 */
	@Override
	public OAuthConfig getOAuthConfig() {
		// TODO Auto-generated method stub
		OAuthConfig config = null;
		RequestContext request = getContainer().getRequest();
		if (request instanceof AttributeContext) {
			config = (OAuthConfig) ((AttributeContext)request).getAttribute(OAuthServlet.OAUTH_CONFIG_KEY);
		}
		
		if (config == null) {
			ApplicationContext application = getContainer().getApplication();
			if (application instanceof AttributeContext) {
				config = (OAuthConfig) ((AttributeContext)application).getAttribute(OAuthServlet.OAUTH_CONFIG_KEY);
			}
		}
		
		return config;
	}
}
