/**
 * 
 */
package za.co.sindi.oauth.server.context.http;

import java.io.IOException;
import java.io.InputStream;

import za.co.sindi.oauth.core.http.Cookie;
import za.co.sindi.oauth.server.context.HeaderContext;
import za.co.sindi.oauth.server.context.ParameterContext;
import za.co.sindi.oauth.server.context.RequestContext;

/**
 * @author Buhake Sindi
 * @since 25 March 2012
 *
 */
public interface HttpRequestContext extends RequestContext, HeaderContext, ParameterContext<String[]> {

	public String getAuthenticationScheme();
	public String getCharacterEncoding();
	public String getContextPath();
	public String getContentType();
	public int getContentLength();
	public Cookie[] getCookies();
	public InputStream getInputStream() throws IOException;
	public String getMethod();
	public String getPathInfo();
	public String getProtocol();
	public String getQueryString();
	public String getRequestURI();
	
	/**
	 * Returns the same value as of the CGI variable <code>SCRIPT_NAME</code> (for HttpServlet, see {@link HttpServletRequest.getServletPath()}).
	
	 * @return
	 */
	public String getScriptName();
}
