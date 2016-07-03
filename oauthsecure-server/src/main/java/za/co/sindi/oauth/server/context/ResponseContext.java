/**
 * 
 */
package za.co.sindi.oauth.server.context;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import javax.servlet.ServletResponse;

/**
 * @author Buhake Sindi
 * @since 25 March 2012
 *
 */
public interface ResponseContext extends Context {

	public OutputStream getOutputStream() throws IOException;
	public void setCharacterEncoding(String encoding);
	public void setContentType(String contentType);
	public void setLocale(Locale locale);
	ServletResponse getServletResponse();
}
