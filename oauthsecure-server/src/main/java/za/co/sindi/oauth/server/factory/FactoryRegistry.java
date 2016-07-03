/**
 * 
 */
package za.co.sindi.oauth.server.factory;

import java.util.logging.Logger;

import za.co.sindi.oauth.server.config.OAuthConfigFactory;
import za.co.sindi.oauth.server.context.OAuthContextFactory;
import za.co.sindi.oauth.server.factory.impl.DefaultFactoryRegistry;
import za.co.sindi.oauth.server.object.BeanFactory;
import za.co.sindi.oauth.server.service.ServiceInvocationFactory;

/**
 * @author Bienfait Sindi
 * @since 15 November 2014
 *
 */
public abstract class FactoryRegistry {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
//	private static ThreadLocal<FactoryRegistry> instanceThread = new ThreadLocal<FactoryRegistry>();
	private static FactoryRegistry instance;
	
	public static final String BEAN_FACTORY = BeanFactory.class.getName();
	public static final String OAUTH_CONFIG_FACTORY = OAuthConfigFactory.class.getName();
	public static final String OAUTH_CONTEXT_FACTORY = OAuthContextFactory.class.getName();
	public static final String SERVICE_INVOCATION_FACTORY = ServiceInvocationFactory.class.getName();
	
//	{
//		registerFactory(BEAN_FACTORY, DefaultBeanFactory.class.getName());
//		registerFactory(OAUTH_CONFIG_FACTORY, DefaultOAuthConfigFactory.class.getName());
//	}

	/**
	 * @return the instance
	 */
	public static FactoryRegistry getInstance() {
//		if (instanceThread.get() == null) {
		if (instance == null) {
			setInstance(new DefaultFactoryRegistry());
		}
		
//		return instanceThread.get();
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public static void setInstance(FactoryRegistry instance) {
		if (instance != null) {
//			instanceThread.set(instance);
			FactoryRegistry.instance = instance;
		}
	}
	
	public abstract Object findFactory(String factoryId);
	public abstract void registerFactory(String factoryId, String className);
	public abstract void releaseFactories();
}
