/**
 * 
 */
package za.co.sindi.oauth.web.servlet.context;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletRequest;

import za.co.sindi.oauth.server.context.AttributeContext;
import za.co.sindi.oauth.server.context.ParameterContext;
import za.co.sindi.oauth.server.context.RequestContext;
import za.co.sindi.oauth.server.context.SessionContext;

/**
 * @author Buhake Sindi
 * @since 25 March 2012
 *
 */
public class ServletRequestContext implements RequestContext, AttributeContext, ParameterContext<String[]> {
	
	private ServletRequest servletRequest;

	/**
	 * 
	 * @param servletRequest
	 */
	public ServletRequestContext(ServletRequest servletRequest) {
		super();
		if (servletRequest == null) {
			throw new IllegalArgumentException("Servlet Request cannot be null.");
		}
		
		this.servletRequest = servletRequest;
	}

	/**
	 * @return the servletRequest
	 */
	public ServletRequest getServletRequest() {
		return servletRequest;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ParameterContext#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return getServletRequest().getParameter(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ParameterContext#getParameterNames()
	 */
	@Override
	public String[] getParameterNames() {
		// TODO Auto-generated method stub
		Enumeration<String> enums = getServletRequest().getParameterNames();
		if (enums == null) {
			return null;
		}
		
		List<String> names = new ArrayList<String>();
		while (enums.hasMoreElements()) {
			names.add(enums.nextElement());
		}
		
		return names.toArray(new String[names.size()]);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ParameterContext#getParameterMap()
	 */
	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		return getServletRequest().getParameterMap();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ParameterContext#getParameterValue(java.lang.String)
	 */
	@Override
	public String[] getParameterValue(String name) {
		// TODO Auto-generated method stub
		return getServletRequest().getParameterValues(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return getServletRequest().getAttribute(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#getAttributeNames()
	 */
	@Override
	public String[] getAttributeNames() {
		// TODO Auto-generated method stub
		Enumeration<String> enums = getServletRequest().getAttributeNames();
		if (enums == null) {
			return null;
		}
		
		List<String> names = new ArrayList<String>();
		while (enums.hasMoreElements()) {
			names.add(enums.nextElement());
		}
		
		return names.toArray(new String[names.size()]);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#removeAttribute(java.lang.String)
	 */
	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub
		getServletRequest().removeAttribute(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String name, Object object) {
		// TODO Auto-generated method stub
		getServletRequest().setAttribute(name, object);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.RequestContext#getLocale()
	 */
	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return getServletRequest().getLocale();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.RequestContext#getLocales()
	 */
	@Override
	public Locale[] getLocales() {
		// TODO Auto-generated method stub
		Enumeration<Locale> enums = getServletRequest().getLocales();
		if (enums == null) {
			return null;
		}
		
		List<Locale> locales = new ArrayList<Locale>();
		while (enums.hasMoreElements()) {
			locales.add(enums.nextElement());
		}
		
		return locales.toArray(new Locale[locales.size()]);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.RequestContext#getScheme()
	 */
	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return getServletRequest().getScheme();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.RequestContext#isSecure()
	 */
	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return getServletRequest().isSecure();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.RequestContext#getServerName()
	 */
	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return getServletRequest().getServerName();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.RequestContext#getServerPort()
	 */
	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return getServletRequest().getServerPort();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.RequestContext#getSessionContext()
	 */
	@Override
	public SessionContext getSessionContext() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not supported.");
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.RequestContext#getSessionContext(boolean)
	 */
	@Override
	public SessionContext getSessionContext(boolean create) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not supported.");
	}
}
