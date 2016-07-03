/**
 * 
 */
package za.co.sindi.oauth.web.servlet;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;

import za.co.sindi.oauth.server.util.Globals;

/**
 * @author Bienfait Sindi
 * @since 02 September 2014
 *
 */
@HandlesTypes({
	za.co.sindi.oauth.server.annotation.Service.class,
	za.co.sindi.oauth.server.service.Service.class
})
public class OAuthServletContainerInitializer implements ServletContainerInitializer {

	private static final Class<? extends Servlet> OAUTH_SERVLET_CLASS = OAuthServlet.class;
//	private static final String OAUTH_SERVLET_CLASSNAME = OAuthServlet.class.getName();
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContainerInitializer#onStartup(java.util.Set, javax.servlet.ServletContext)
	 */
	public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		if (isOAuthMappingsSet(classes, servletContext)) {
			Map<String,? extends ServletRegistration> registrations = servletContext.getServletRegistrations();
			for (ServletRegistration registration : registrations.values()) {
				if (OAUTH_SERVLET_CLASS.getName().equals(registration.getClassName())) {
					//We have a servlet defined, do nothing....
					return ;
				}
			}
			
			ServletRegistration.Dynamic registration = servletContext.addServlet(OAUTH_SERVLET_CLASS.getSimpleName(), OAUTH_SERVLET_CLASS);
			registration.addMapping("/api/*");
			registration.setLoadOnStartup(1);
			
			//Add listener
			servletContext.addListener(OAuthServletApplicationInitializer.class);
		}
	}
	
	private boolean isOAuthMappingsSet(Set<Class<?>> classes, ServletContext servletContext) {
		if (classes != null && !classes.isEmpty()) {
			return true;
		}
		
		try {
			return servletContext.getResource("/WEB-INF/" + Globals.OAUTH_CONFIG_FILENAME) != null;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		return false;
	}
}
