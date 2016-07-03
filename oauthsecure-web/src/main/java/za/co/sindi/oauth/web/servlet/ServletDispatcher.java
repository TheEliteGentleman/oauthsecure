/**
 * 
 */
package za.co.sindi.oauth.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Bienfait Sindi
 * @since 16 February 2016
 *
 */
public interface ServletDispatcher {

	public void destroy();
	public void init(ServletConfig servletConfig);
	public void execute(ServletRequest request, ServletResponse response) throws ServletException, IOException;
}
