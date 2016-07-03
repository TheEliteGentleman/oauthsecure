/**
 * 
 */
package za.co.sindi.oauth.web.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import za.co.sindi.oauth.webapp.OAuthInitializer;
import za.co.sindi.oauth.webapp.impl.DefaultOAuthInitializer;

/**
 * @author Bienfait Sindi
 * @since 02 September 2014
 *
 */
public class OAuthServlet extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7062330598264978585L;
	private static final Logger LOGGER = Logger.getLogger(OAuthServlet.class.getName());
	
	public static final String OAUTH_CONFIG_KEY = "za.co.sindi.oauth.config.OAUTH_CONFIG_KEY";
	
	private OAuthInitializer initializer;
	private ServletDispatcher servletDispatcher;

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletContext servletContext = getServletContext();
		Boolean oauthInitDone = (Boolean) servletContext.getAttribute(OAuthServletApplicationInitializer.OAUTH_INIT_DONE);
		if (oauthInitDone == null || !oauthInitDone.booleanValue()) {
			if (LOGGER.isLoggable(Level.WARNING)) {
				LOGGER.warning("ServletContextListener is not yet initialized.");
			}
			
			getInitializer().initialize(servletContext);
			servletContext.setAttribute(OAuthServletApplicationInitializer.OAUTH_INIT_DONE, true);
		}
		
		servletDispatcher = new DefaultServletDispatcher();
		servletDispatcher.init(getServletConfig());
	}

	/**
	 * @return the initializer
	 */
	protected OAuthInitializer getInitializer() {
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

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		servletDispatcher.execute(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		servletDispatcher.destroy();
		servletDispatcher = null;
	}
}
