/**
 * 
 */
package za.co.sindi.oauth.cdi;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import za.co.sindi.oauth.server.factory.FactoryRegistry;

/**
 * @author Bienfait Sindi
 * @since 05 January 2016
 *
 */
public class CDIServletContextListener implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		FactoryRegistry factoryRegistry = FactoryRegistry.getInstance();
		factoryRegistry.registerFactory(FactoryRegistry.BEAN_FACTORY, CDIBeanFactory.class.getName());
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}
}
