/**
 * 
 */
package za.co.sindi.oauth.server.context.http;

import java.io.IOException;

import za.co.sindi.oauth.core.http.Cookie;
import za.co.sindi.oauth.server.context.HeaderContext;
import za.co.sindi.oauth.server.context.ResponseContext;

/**
 * @author Buhake Sindi
 * @since 25 March 2012
 *
 */
public interface HttpResponseContext extends ResponseContext, HeaderContext {

	public void addCookie(Cookie cookie);
	public void addHeader(String name, String value);
	public String encodeRedirectURL(String url);
	public String encodeURL(String url);
	public void sendError(int statusCode, String message) throws IOException;
	public void sendError(int statusCode) throws IOException;
	public void sendRedirect(String url) throws IOException;
	public void setStatusCode(int statusCode);
}
