/**
 * 
 */
package za.co.sindi.oauth.server.object.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.object.BeanFactory;
import za.co.sindi.oauth.server.util.ApplicationUtils;

/**
 * @author Bienfait Sindi
 * @since 18 November 2014
 *
 */
public class DefaultBeanFactory implements BeanFactory {

	private static final Logger LOGGER = Logger.getLogger(DefaultBeanFactory.class.getName());
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.object.BeanFactory#getBean(java.lang.String)
	 */
	@Override
	public Object getBean(String className) {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(className != null && !className.isEmpty(), "Class name may not be null.");
		try {
			return ApplicationUtils.applicationInstance(className);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			if (LOGGER.isLoggable(Level.SEVERE)) {
				LOGGER.log(Level.SEVERE, "Error creating new instance of bean " + className, e);
			}
			
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.object.BeanFactory#destroyBean(java.lang.Object)
	 */
	@Override
	public void destroyBean(Object bean) {
		// TODO Auto-generated method stub
		
	}
}
