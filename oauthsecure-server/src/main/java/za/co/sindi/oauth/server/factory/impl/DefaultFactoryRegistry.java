/**
 * 
 */
package za.co.sindi.oauth.server.factory.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.config.impl.DefaultOAuthConfigFactory;
import za.co.sindi.oauth.server.factory.FactoryRegistry;
import za.co.sindi.oauth.server.object.impl.DefaultBeanFactory;
import za.co.sindi.oauth.server.util.ApplicationUtils;

/**
 * @author Bienfait Sindi
 * @since 15 November 2014
 *
 */
public class DefaultFactoryRegistry extends FactoryRegistry {
	
	private Map<String, Object> factoryMap = new ConcurrentHashMap<String, Object>();
	private transient ClassLoader classLoader;

	/**
	 * 
	 */
	public DefaultFactoryRegistry() {
		super();
		// TODO Auto-generated constructor stub
		registerFactory(BEAN_FACTORY, DefaultBeanFactory.class.getName());
		registerFactory(OAUTH_CONFIG_FACTORY, DefaultOAuthConfigFactory.class.getName());
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.factory.FactoryRegistry#registerFactory(java.lang.String, java.lang.Class)
	 */
	/**
	 * @param classLoader
	 */
	public DefaultFactoryRegistry(ClassLoader classLoader) {
		this();
		this.classLoader = classLoader;
	}

	/**
	 * @return the classLoader
	 */
	public ClassLoader getClassLoader() {
		if (classLoader == null) {
			classLoader = ApplicationUtils.getClassLoader();
		}
		
		return classLoader;
	}

	/**
	 * @param classLoader the classLoader to set
	 */
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.factory.FactoryRegistry#findFactory(java.lang.String)
	 */
	@Override
	public Object findFactory(String factoryId) {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(factoryId != null && !factoryId.isEmpty(), "Factory ID may not be null or empty.");
		PreConditions.checkState(!factoryMap.isEmpty(), "There are no factories registered.");
		
		return factoryMap.get(factoryId);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.factory.FactoryRegistry#registerFactory(java.lang.String, java.lang.String)
	 */
	@Override
	public void registerFactory(String factoryId, String className) {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(factoryId != null && !factoryId.isEmpty(), "Factory ID may not be null or empty.");
		PreConditions.checkArgument(className != null && !className.isEmpty(), "Class name may not be null or empty.");
		
		try {
			factoryMap.put(factoryId, ApplicationUtils.applicationInstance(className, getClassLoader()));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			if (LOGGER.isLoggable(Level.SEVERE)) {
				LOGGER.severe("Error loading class " + className);
			}
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.factory.FactoryRegistry#releaseFactories()
	 */
	@Override
	public void releaseFactories() {
		// TODO Auto-generated method stub
		factoryMap.clear();
	}
}
