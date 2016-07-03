/**
 * 
 */
package za.co.sindi.oauth.web.config;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterConfig;

import za.co.sindi.oauth.server.config.ServerConfig;

/**
 * @author Bienfait Sindi
 * @since 11 October 2014
 *
 */
public class ServletFilterServerConfig implements ServerConfig {

	private FilterConfig filterConfig;
	
	/**
	 * @param filterConfig
	 */
	public ServletFilterServerConfig(FilterConfig filterConfig) {
		super();
		if (filterConfig == null) {
			throw new IllegalArgumentException("No FiterConfig provided.");
		}
		
		this.filterConfig = filterConfig;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.config.WebConfig#getConfigName()
	 */
	@Override
	public String getConfigName() {
		// TODO Auto-generated method stub
		return filterConfig.getFilterName();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.config.WebConfig#getInitParameter(java.lang.String)
	 */
	@Override
	public String getInitParameter(String name) {
		// TODO Auto-generated method stub
		return filterConfig.getInitParameter(name);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.config.WebConfig#getInitParameterNames()
	 */
	@Override
	public String[] getInitParameterNames() {
		// TODO Auto-generated method stub
		List<String> initParams = null;
		if (filterConfig.getInitParameterNames() != null) {
			for (Enumeration<String> _enum  = filterConfig.getInitParameterNames(); _enum.hasMoreElements(); ) {
				if (initParams != null) {
					initParams = new ArrayList<String>();
				}
				
				initParams.add(_enum.nextElement());
			}
		}
		
		return (initParams == null) ? null : initParams.toArray(new String[initParams.size()]);
	}
}
