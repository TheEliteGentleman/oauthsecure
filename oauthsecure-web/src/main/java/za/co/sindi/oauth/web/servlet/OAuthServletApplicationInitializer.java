/**
 * 
 */
package za.co.sindi.oauth.web.servlet;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import za.co.sindi.oauth.webapp.OAuthInitializer;
import za.co.sindi.oauth.webapp.impl.DefaultOAuthInitializer;

/**
 * @author Bienfait Sindi
 * @since 29 September 2014
 *
 */
//@WebListener
public class OAuthServletApplicationInitializer implements ServletContextListener {

	private static final Logger LOGGER = Logger.getLogger(OAuthServletApplicationInitializer.class.getName());
	public static final String OAUTH_INIT_DONE = "za.co.sindi.oauth.web.servlet.OAuthServletApplicationInitializer.OAUTH_INIT_DONE";
	
	private OAuthInitializer initializer;
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext = sce.getServletContext();
		Boolean oauthInitDone = (Boolean) servletContext.getAttribute(OAUTH_INIT_DONE);
		if (oauthInitDone == null || oauthInitDone.booleanValue() == false) {
			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.info("Initializing ServletContext." + this.getClass().getName());
			}
			
			getInitializer().initialize(servletContext);
			servletContext.setAttribute(OAUTH_INIT_DONE, true);
		} else {
			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.info("OAuthSecure is already initialized.");
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext servletContext = sce.getServletContext();
		if (LOGGER.isLoggable(Level.INFO)) {
			LOGGER.info("Destroying ServletContext." + this.getClass().getName());
		}
		
		getInitializer().destroy(servletContext);
		servletContext.removeAttribute(OAUTH_INIT_DONE);
		
		if (LOGGER.isLoggable(Level.INFO)) {
			LOGGER.info("ServletContext destroyed.");
		}
		
		servletContext = null;
	}

	/**
	 * @return the initializer
	 */
	public OAuthInitializer getInitializer() {
		if (initializer == null) {
			initializer = new DefaultOAuthInitializer();
		}
		
		return initializer;
	}

	/**
	 * @param initializer the initializer to set
	 */
	public void setInitializer(OAuthInitializer initializer) {
		this.initializer = initializer;
	}
}
