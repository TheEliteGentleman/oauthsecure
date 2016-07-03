/**
 * 
 */
package za.co.sindi.oauth.web.servlet.context.http;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import za.co.sindi.oauth.core.http.Cookie;
import za.co.sindi.oauth.server.context.http.HttpResponseContext;
import za.co.sindi.oauth.web.servlet.context.ServletResponseContext;

/**
 * @author Buhake Sindi
 * @since 13 April 2012
 *
 */
public class HttpServletResponseContext extends ServletResponseContext implements HttpResponseContext {

	/**
	 * @param servletResponse
	 */
	public HttpServletResponseContext(HttpServletResponse servletResponse) {
		super(servletResponse);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.web.servlet.ServletResponseContext#getServletResponse()
	 */
	@Override
	public HttpServletResponse getServletResponse() {
		// TODO Auto-generated method stub
		return (HttpServletResponse) super.getServletResponse();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HeaderContext#containsHeader(java.lang.String)
	 */
	@Override
	public boolean containsHeader(String name) {
		// TODO Auto-generated method stub
		return getServletResponse().containsHeader(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpResponseContext#encodeRedirectURL(java.lang.String)
	 */
	@Override
	public String encodeRedirectURL(String url) {
		// TODO Auto-generated method stub
		return getServletResponse().encodeRedirectURL(url);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpResponseContext#encodeURL(java.lang.String)
	 */
	@Override
	public String encodeURL(String url) {
		// TODO Auto-generated method stub
		return getServletResponse().encodeURL(url);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HeaderContext#getHeader(java.lang.String)
	 */
	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return getServletResponse().getHeader(name);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HeaderContext#getHeaderNames()
	 */
	@Override
	public String[] getHeaderNames() {
		// TODO Auto-generated method stub
		Collection<String> headers = getServletResponse().getHeaderNames();
		if (headers == null) {
			return null;
		}
		
		return headers.toArray(new String[headers.size()]);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HeaderContext#getHeaders(java.lang.String)
	 */
	@Override
	public String[] getHeaders(String name) {
		// TODO Auto-generated method stub
		Collection<String> headers = getServletResponse().getHeaders(name);
		if (headers == null) {
			return null;
		}
		
		return headers.toArray(new String[headers.size()]);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpResponseContext#addCookie(net.oauth.transport.http.Cookie)
	 */
	@Override
	public void addCookie(Cookie cookie) {
		// TODO Auto-generated method stub
		if (cookie != null) {
			javax.servlet.http.Cookie servletCookie = new javax.servlet.http.Cookie(cookie.getName(), cookie.getValue());
			servletCookie.setComment(cookie.getComment());
			servletCookie.setDomain(cookie.getDomain());
			servletCookie.setMaxAge(cookie.getMaxAge());
			servletCookie.setPath(cookie.getPath());
			servletCookie.setSecure(cookie.isSecure());
			servletCookie.setVersion(cookie.getVersion());
			
			getServletResponse().addCookie(servletCookie);
		}
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpResponseContext#addHeader(java.lang.String, java.lang.String)
	 */
	@Override
	public void addHeader(String name, String value) {
		// TODO Auto-generated method stub
		getServletResponse().addHeader(name, value);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.http.HttpResponseContext#sendError(int, java.lang.String)
	 */
	@Override
	public void sendError(int statusCode, String message) throws IOException {
		// TODO Auto-generated method stub
		getServletResponse().sendError(statusCode, message);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.http.HttpResponseContext#sendError(int)
	 */
	@Override
	public void sendError(int statusCode) throws IOException {
		// TODO Auto-generated method stub
		getServletResponse().sendError(statusCode);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpResponseContext#sendRedirect(java.lang.String)
	 */
	@Override
	public void sendRedirect(String url) throws IOException {
		// TODO Auto-generated method stub
		getServletResponse().sendRedirect(url);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.HttpResponseContext#setStatusCode(int)
	 */
	@Override
	public void setStatusCode(int statusCode) {
		// TODO Auto-generated method stub
		getServletResponse().setStatus(statusCode);
	}
}
