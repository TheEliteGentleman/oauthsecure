/**
 * 
 */
package za.co.sindi.oauth.webapp;

import javax.servlet.ServletContext;

/**
 * @author Bienfait Sindi
 * @since 17 October 2014
 *
 */
public interface OAuthInitializer {

	public void initialize(ServletContext servletContext);
	
	public void destroy(ServletContext context);
}
