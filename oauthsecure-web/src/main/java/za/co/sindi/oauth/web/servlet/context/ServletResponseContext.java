/**
 * 
 */
package za.co.sindi.oauth.web.servlet.context;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import javax.servlet.ServletResponse;

import za.co.sindi.oauth.server.context.ResponseContext;

/**
 * @author Buhake Sindi
 * @since 25 March 2012
 *
 */
public class ServletResponseContext implements ResponseContext {

	private ServletResponse servletResponse;
	
	/**
	 * @param servletContext
	 */
	public ServletResponseContext(ServletResponse servletResponse) {
		super();
		if (servletResponse == null) {
			throw new IllegalArgumentException("ServletResponse may not be null.");
		}
		
		this.servletResponse = servletResponse;
	}

	/**
	 * @return the servletResponse
	 */
	public ServletResponse getServletResponse() {
		return servletResponse;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ResponseContext#getOutputStream()
	 */
	@Override
	public OutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return getServletResponse().getOutputStream();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ResponseContext#setCharacterEncoding(java.lang.String)
	 */
	@Override
	public void setCharacterEncoding(String encoding) {
		// TODO Auto-generated method stub
		getServletResponse().setCharacterEncoding(encoding);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ResponseContext#setContentType(java.lang.String)
	 */
	@Override
	public void setContentType(String contentType) {
		// TODO Auto-generated method stub
		getServletResponse().setContentType(contentType);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.context.ResponseContext#setLocale(java.util.Locale)
	 */
	@Override
	public void setLocale(Locale locale) {
		// TODO Auto-generated method stub
		getServletResponse().setLocale(locale);
	}
}
