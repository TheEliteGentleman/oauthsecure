/**
 * 
 */
package za.co.sindi.oauth.web.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.context.http.HttpResponseContext;
import za.co.sindi.oauth.web.servlet.context.http.HttpServletRequestContext;
import za.co.sindi.oauth.web.servlet.context.http.HttpServletResponseContext;
import za.co.sindi.servlet.filter.HttpFilter;

/**
 * @author Bienfait Sindi
 * @since 19 January 2016
 *
 */
public class OAuth1ProtectedResourceFilter extends HttpFilter {

	/* (non-Javadoc)
	 * @see za.co.sindi.servlet.filter.HttpFilter#doFilter(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpSession, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpRequestContext httpRequest = new HttpServletRequestContext(request);
		HttpResponseContext httpResponse = new HttpServletResponseContext(response);
		
	}
}
