/**
 * 
 */
package za.co.sindi.oauth.web.servlet;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import za.co.sindi.oauth.server.Path;
import za.co.sindi.oauth.server.RequestPath;
import za.co.sindi.oauth.server.http.auth.HttpPath;

/**
 * @author Bienfait Sindi
 * @since 16 February 2016
 *
 */
public class HttpServletRequestPath implements RequestPath {

	private Path path;
	private URI absoluteURI;
	private URI requestURI;
	
	/**
	 * @param request
	 */
	public HttpServletRequestPath(HttpServletRequest request) {
		super();
		if (request == null) {
			throw new IllegalArgumentException("HttpServletRequest is null.");
		}
		
		String absolutePath = request.getRequestURL().toString();
		this.absoluteURI = URI.create(absolutePath);
		if (request.getQueryString() == null) {
			this.requestURI = URI.create(absolutePath);
		} else {
			this.requestURI = URI.create(absolutePath + "?" + request.getQueryString());
		}
		
		String servletContextPath = request.getContentType() + request.getServletPath();
		String path = absolutePath.substring(absolutePath.indexOf(servletContextPath) + servletContextPath.length());
		this.path = new HttpPath(path);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.RequestPath#getPath()
	 */
	@Override
	public Path getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.RequestPath#absolutePathURI()
	 */
	@Override
	public URI absolutePathURI() {
		// TODO Auto-generated method stub
		return absoluteURI;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.RequestPath#getRequestURI()
	 */
	@Override
	public URI getRequestURI() {
		// TODO Auto-generated method stub
		return requestURI;
	}
}
