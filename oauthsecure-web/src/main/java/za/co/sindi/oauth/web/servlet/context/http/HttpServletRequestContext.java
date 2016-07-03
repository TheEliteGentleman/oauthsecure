/**
 * 
 */
package za.co.sindi.oauth.web.servlet.context.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import za.co.sindi.oauth.core.http.Cookie;
import za.co.sindi.oauth.core.http.cookie.impl.HttpSetCookie;
import za.co.sindi.oauth.server.context.SessionContext;
import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.web.servlet.context.ServletRequestContext;

/**
 * @author Buhake Sindi
 * @since 25 March 2012
 *
 */
public class HttpServletRequestContext extends ServletRequestContext implements HttpRequestContext {

	/**
	 * @param servletRequest
	 */
	public HttpServletRequestContext(HttpServletRequest servletRequest) {
		super(servletRequest);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.server.context.servlet.ServletRequestContext#getServletRequest()
	 */
	@Override
	public HttpServletRequest getServletRequest() {
		// TODO Auto-generated method stub
		return (HttpServletRequest) super.getServletRequest();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HeaderContext#containsHeader(java.lang.String)
	 */
	@Override
	public boolean containsHeader(String name) {
		// TODO Auto-generated method stub
		return getHeader(name) != null;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HeaderContext#getHeader(java.lang.String)
	 */
	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return getServletRequest().getHeader(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HeaderContext#getHeaderNames()
	 */
	@Override
	public String[] getHeaderNames() {
		// TODO Auto-generated method stub
		Enumeration<String> enums = getServletRequest().getHeaderNames();
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
	 * @see com.neurologic.oauth.core.context.HeaderContext#getHeaders(java.lang.String)
	 */
	@Override
	public String[] getHeaders(String name) {
		// TODO Auto-generated method stub
		Enumeration<String> enums = getServletRequest().getHeaders(name);
		if (enums == null) {
			return null;
		}
		
		List<String> headers = new ArrayList<String>();
		while (enums.hasMoreElements()) {
			headers.add(enums.nextElement());
		}
		
		return headers.toArray(new String[headers.size()]);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.http.HttpRequestContext#getAuthenticationScheme()
	 */
	@Override
	public String getAuthenticationScheme() {
		// TODO Auto-generated method stub
		return getServletRequest().getAuthType();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getCharacterEncoding()
	 */
	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return getServletRequest().getCharacterEncoding();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getContextPath()
	 */
	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return getServletRequest().getContextPath();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getContentType()
	 */
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return getServletRequest().getContentType();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getContentLength()
	 */
	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return getServletRequest().getContentLength();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getCookies()
	 */
	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		javax.servlet.http.Cookie[] cookies = getServletRequest().getCookies();
		if (cookies == null) {
			return null;
		}
		
		int cookieSize = cookies.length;
		HttpSetCookie[] convertedCookies = new HttpSetCookie[cookieSize];
		for (int i = 0; i < cookieSize; i++) {
			convertedCookies[i] = new HttpSetCookie(cookies[i].getName(), cookies[i].getValue());
			convertedCookies[i].setComment(cookies[i].getComment());
			convertedCookies[i].setDomain(cookies[i].getDomain());
			convertedCookies[i].setMaxAge(cookies[i].getMaxAge());
			convertedCookies[i].setPath(cookies[i].getPath());
			convertedCookies[i].setVersion(cookies[i].getVersion());
		}
		
		return convertedCookies;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return getServletRequest().getInputStream();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getMethod()
	 */
	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return getServletRequest().getMethod();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getPathInfo()
	 */
	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return getServletRequest().getPathInfo();
	}
	
	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getProtocol()
	 */
	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return getServletRequest().getProtocol();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getQueryString()
	 */
	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return getServletRequest().getQueryString();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getRequestURI()
	 */
	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return getServletRequest().getRequestURI();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpRequestContext#getScriptName()
	 */
	@Override
	public String getScriptName() {
		// TODO Auto-generated method stub
		return getServletRequest().getServletPath();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.servlet.context.ServletRequestContext#getSessionContext()
	 */
	@Override
	public SessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return new HttpSessionContext(getServletRequest().getSession());
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.servlet.context.ServletRequestContext#getSessionContext(boolean)
	 */
	@Override
	public SessionContext getSessionContext(boolean create) {
		// TODO Auto-generated method stub
		return new HttpSessionContext(getServletRequest().getSession(create));
	}
}
