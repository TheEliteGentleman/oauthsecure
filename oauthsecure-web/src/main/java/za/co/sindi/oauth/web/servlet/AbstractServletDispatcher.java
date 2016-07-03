/**
 * 
 */
package za.co.sindi.oauth.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * @author Bienfait Sindi
 * @since 16 February 2016
 *
 */
public abstract class AbstractServletDispatcher implements ServletDispatcher {

	private ServletConfig servletConfig;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.servlet.ServletDispatcher#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		doDestroy();
		servletConfig = null;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.servlet.ServletDispatcher#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig servletConfig) {
		// TODO Auto-generated method stub
		this.servletConfig = servletConfig;
		init();
	}

	/**
	 * @return the servletConfig
	 */
	protected ServletConfig getServletConfig() {
		return servletConfig;
	}

	/**
	 * @return the servletContext
	 */
	protected ServletContext getServletContext() {
		return getServletConfig().getServletContext();
	}

	protected void init() {
		
	}
	
	protected void doDestroy() {
		
	}
}
