/**
 * 
 */
package za.co.sindi.oauth.web.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.context.http.HttpResponseContext;
import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory;
import za.co.sindi.oauth.server.http.auth.TokenCredential;
import za.co.sindi.oauth.server.http.auth.bearer.BearerAuthenticationBuilderFactory;
import za.co.sindi.oauth.server.http.auth.bearer.BearerChallengeAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.bearer.BearerCredentialsAuthenticationScheme;
import za.co.sindi.oauth.web.servlet.context.http.HttpServletRequestContext;
import za.co.sindi.oauth.web.servlet.context.http.HttpServletResponseContext;
import za.co.sindi.servlet.filter.GenericFilter;

/**
 * @author Bienfait Sindi
 * @since 01 October 2014
 *
 */
public class OAuth2ProtectedResourceFilter extends GenericFilter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			
			doFilter(httpRequest, httpResponse, chain);
		}
	}
	
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpRequestContext httpRequest = new HttpServletRequestContext(request);
		HttpResponseContext httpResponse = new HttpServletResponseContext(response);
		AuthenticationSchemeBuilderFactory<TokenCredential, BearerCredentialsAuthenticationScheme, BearerChallengeAuthenticationScheme> authenticationSchemeBuilderFactory = new BearerAuthenticationBuilderFactory();
	}
}
