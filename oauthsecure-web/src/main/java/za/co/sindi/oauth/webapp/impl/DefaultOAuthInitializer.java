/**
 * 
 */
package za.co.sindi.oauth.webapp.impl;

import java.util.logging.Level;

import javax.servlet.ServletContext;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.config.OAuthConfigFactory;
import za.co.sindi.oauth.server.config.element.OAuthConfig;
import za.co.sindi.oauth.server.exception.ConfigurationException;
import za.co.sindi.oauth.server.factory.FactoryRegistry;
import za.co.sindi.oauth.server.scanner.PathAwareResourceScanner;
import za.co.sindi.oauth.server.scanner.impl.ResourceContextResourceScanner;
import za.co.sindi.oauth.server.service.impl.DefaultServiceInvocationFactory;
import za.co.sindi.oauth.server.util.Globals;
import za.co.sindi.oauth.web.servlet.OAuthServlet;
import za.co.sindi.oauth.web.servlet.OAuthServletApplicationInitializer;
import za.co.sindi.oauth.web.servlet.context.ServletApplicationContext;
import za.co.sindi.oauth.web.servlet.context.ServletOAuthContextFactory;
import za.co.sindi.oauth.webapp.AbstractOAuthInitializer;
import za.co.sindi.oauth.webapp.OAuthInitializer;

/**
 * @author Bienfait Sindi
 * @since 14 November 2014
 *
 */
public class DefaultOAuthInitializer extends AbstractOAuthInitializer implements OAuthInitializer {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.webapp.OAuthInitializer#initialize(javax.servlet.ServletContext)
	 */
	@Override
	public void initialize(ServletContext servletContext) {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(servletContext != null, "ServletContext is required.");
		try {
			FactoryRegistry factoryRegistry = FactoryRegistry.getInstance();
			OAuthConfigFactory oauthConfigFactory = (OAuthConfigFactory) factoryRegistry.findFactory(FactoryRegistry.OAUTH_CONFIG_FACTORY);
			PreConditions.checkState(oauthConfigFactory != null, "OAuthConfigFactory was not registered.");
			PathAwareResourceScanner scanner = new ResourceContextResourceScanner(new ServletApplicationContext(servletContext));
			//The ordering is important here. The "oauth-config.xml" file takes higher priority so parse it last.
			scanner.addResourcePath("/WEB-INF/lib/");
			scanner.addResourcePath("/WEB-INF/classes/");
			scanner.addResourcePath("/META-INF/" + Globals.OAUTH_CONFIG_FILENAME);
			oauthConfigFactory.addResourceScanner(scanner);
			OAuthConfig oauthConfig = oauthConfigFactory.create();
			servletContext.setAttribute(OAuthServlet.OAUTH_CONFIG_KEY, oauthConfig);
			
			//If there is no OAuthContextFactory set, default to a built-in ServletOAuthContextFactory
			if (factoryRegistry.findFactory(FactoryRegistry.OAUTH_CONTEXT_FACTORY) == null) {
				factoryRegistry.registerFactory(FactoryRegistry.OAUTH_CONTEXT_FACTORY, ServletOAuthContextFactory.class.getName());
			}
			
			//If there is no ServiceInvocationFactory set, default to a built-in ServiceInvocationFactory
			if (factoryRegistry.findFactory(FactoryRegistry.SERVICE_INVOCATION_FACTORY) == null) {
				factoryRegistry.registerFactory(FactoryRegistry.SERVICE_INVOCATION_FACTORY, DefaultServiceInvocationFactory.class.getName());
			}
			
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			if (LOGGER.isLoggable(Level.SEVERE)) {
				LOGGER.log(Level.SEVERE, "Error encountered while creating an OAuthConfig.", e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.webapp.OAuthInitializer#destroy(javax.servlet.ServletContext)
	 */
	@Override
	public void destroy(ServletContext servletContext) {
		// TODO Auto-generated method stub
		FactoryRegistry.getInstance().releaseFactories();
//		servletContext.removeAttribute(OAuthServlet.SERVICE_PROCESSOR_KEY);
		servletContext.removeAttribute(OAuthServlet.OAUTH_CONFIG_KEY);
		servletContext.removeAttribute(OAuthServletApplicationInitializer.OAUTH_INIT_DONE);
	}
}
