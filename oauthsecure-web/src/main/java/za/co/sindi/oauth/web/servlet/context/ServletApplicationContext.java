/**
 * 
 */
package za.co.sindi.oauth.web.servlet.context;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import za.co.sindi.oauth.server.context.ApplicationContext;
import za.co.sindi.oauth.server.context.AttributeContext;
import za.co.sindi.oauth.server.context.ResourceContext;

/**
 * @author Buhake Sindi
 * @since 25 March 2012
 *
 */
public class ServletApplicationContext implements ApplicationContext, ResourceContext, AttributeContext {

	private javax.servlet.ServletContext servletContext;
	
	/**
	 * @param servletContext
	 */
	public ServletApplicationContext(ServletContext servletContext) {
		super();
		if (servletContext == null) {
			throw new IllegalArgumentException("Servlet Context may not be null.");
		}
		
		this.servletContext = servletContext;
	}

	/**
	 * @return the servletContext
	 */
	protected javax.servlet.ServletContext getServletContext() {
		return servletContext;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return getServletContext().getAttribute(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#getAttributeNames()
	 */
	@Override
	public String[] getAttributeNames() {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		Enumeration<String> enums = getServletContext().getAttributeNames();
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
		getServletContext().removeAttribute(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.AttributeContext#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String name, Object object) {
		// TODO Auto-generated method stub
		getServletContext().setAttribute(name, object);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.web.ResourceContext#getRealPath(java.lang.String)
	 */
	@Override
	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return getServletContext().getRealPath(path);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.web.ResourceContext#getResource(java.lang.String)
	 */
	@Override
	public URL getResource(String path) throws MalformedURLException {
		// TODO Auto-generated method stub
		return getServletContext().getResource(path);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.web.ResourceContext#getResourceAsStream(java.lang.String)
	 */
	@Override
	public InputStream getResourceAsStream(String path) {
		// TODO Auto-generated method stub
		return getServletContext().getResourceAsStream(path);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.web.ResourceContext#getResourcePaths(java.lang.String)
	 */
	@Override
	public Set<String> getResourcePaths(String path) {
		// TODO Auto-generated method stub
		return getServletContext().getResourcePaths(path);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ApplicationContext#getMimeType(java.lang.String)
	 */
	@Override
	public String getMimeType(String file) {
		// TODO Auto-generated method stub
		return getServletContext().getMimeType(file);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ApplicationContext#getServerInfo()
	 */
	@Override
	public String getServerInfo() {
		// TODO Auto-generated method stub
		return getServletContext().getServerInfo();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ApplicationContext#log(java.lang.String)
	 */
	@Override
	public void log(String message) {
		// TODO Auto-generated method stub
		getServletContext().log(message);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ApplicationContext#log(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void log(String message, Throwable throwable) {
		// TODO Auto-generated method stub
		getServletContext().log(message, throwable);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ApplicationContext#getContext()
	 */
	@Override
	public Object getContext() {
		// TODO Auto-generated method stub
		return getServletContext();
	}
}
