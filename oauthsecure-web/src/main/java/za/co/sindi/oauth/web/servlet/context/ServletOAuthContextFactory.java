/**
 * 
 */
package za.co.sindi.oauth.web.servlet.context;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.context.OAuthContextFactory;
import za.co.sindi.oauth.web.servlet.HttpServletContainerContext;

/**
 * @author Bienfait Sindi
 * @since 16 November 2014
 *
 */
public class ServletOAuthContextFactory implements OAuthContextFactory {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.OAuthContextFactory#createOAuthContext(java.lang.Object, java.lang.Object)
	 */
	@Override
	public OAuthContext createOAuthContext(Object request, Object response) {
		// TODO Auto-generated method stub
		ServletContainerContext container = null;
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			container = new HttpServletContainerContext((HttpServletRequest)request, (HttpServletResponse)response);
		} else if (request instanceof ServletRequest && response instanceof ServletResponse) {
			container = new ServletContainerContext((ServletRequest)request, (ServletResponse)response);
		}
		
		PreConditions.checkSate(container != null, "Couldn't create a ContainerContext.");
		return new ServletOAuthContext(container);
	}
}
