/**
 * 
 */
package za.co.sindi.oauth.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.sindi.oauth.web.servlet.context.ServletContainerContext;
import za.co.sindi.oauth.web.servlet.context.http.HttpServletRequestContext;
import za.co.sindi.oauth.web.servlet.context.http.HttpServletResponseContext;

/**
 * @author Bienfait Sindi
 * @since 23 November 2014
 *
 */
public class HttpServletContainerContext extends ServletContainerContext {

	/**
	 * @param servletRequest
	 * @param servletResponse
	 */
	public HttpServletContainerContext(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		super(servletRequest, servletResponse);
		// TODO Auto-generated constructor stub
		setRequest(new HttpServletRequestContext(servletRequest));
		setResponse(new HttpServletResponseContext(servletResponse));
	}
}
