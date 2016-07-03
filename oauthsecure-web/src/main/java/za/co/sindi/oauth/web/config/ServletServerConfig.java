/**
 * 
 */
package za.co.sindi.oauth.web.config;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;

import za.co.sindi.oauth.server.config.ServerConfig;

/**
 * @author Bienfait Sindi
 * @since 11 October 2014
 *
 */
public class ServletServerConfig implements ServerConfig {

	private ServletConfig servletConfig;
	
	/**
	 * @param servletConfig
	 */
	public ServletServerConfig(ServletConfig servletConfig) {
		super();
		if (servletConfig == null) {
			throw new IllegalArgumentException("No FiterConfig provided.");
		}
		
		this.servletConfig = servletConfig;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.config.WebConfig#getConfigName()
	 */
	@Override
	public String getConfigName() {
		// TODO Auto-generated method stub
		return servletConfig.getServletName();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.config.WebConfig#getInitParameter(java.lang.String)
	 */
	@Override
	public String getInitParameter(String name) {
		// TODO Auto-generated method stub
		return servletConfig.getInitParameter(name);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.config.WebConfig#getInitParameterNames()
	 */
	@Override
	public String[] getInitParameterNames() {
		// TODO Auto-generated method stub
		List<String> initParams = null;
		if (servletConfig.getInitParameterNames() != null) {
			for (Enumeration<String> _enum  = servletConfig.getInitParameterNames(); _enum.hasMoreElements(); ) {
				if (initParams != null) {
					initParams = new ArrayList<String>();
				}
				
				initParams.add(_enum.nextElement());
			}
		}
		
		return (initParams == null) ? null : initParams.toArray(new String[initParams.size()]);
	}
}
