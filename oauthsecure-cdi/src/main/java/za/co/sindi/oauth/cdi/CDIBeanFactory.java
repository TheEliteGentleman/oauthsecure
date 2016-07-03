/**
 * 
 */
package za.co.sindi.oauth.cdi;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;

import za.co.sindi.commons.javaee.util.CDI;
import za.co.sindi.oauth.server.object.BeanFactory;
import za.co.sindi.oauth.server.util.ApplicationUtils;

/**
 * @author Bienfait Sindi
 * @since 05 January 2015
 *
 */
public class CDIBeanFactory implements BeanFactory {

	private BeanManager beanManager = CDI.getBeanManager();
	@SuppressWarnings("rawtypes")
	private CreationalContext creationalContext;
	private Map<Class<?>, InjectionTarget<?>> injectionTargetCache = new ConcurrentHashMap<>();
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.object.BeanFactory#getBean(java.lang.String)
	 */
	/**
	 * 
	 */
	public CDIBeanFactory() {
		super();
		// TODO Auto-generated constructor stub
		beanManager = CDI.getBeanManager();
//		creationalContext = beanManager.createCreationalContext(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getBean(String beanName) {
		// TODO Auto-generated method stub
		Object bean = null;
		//First try by class name
		try {
			Class<?> clazz = ApplicationUtils.applicationClass(beanName);
//			bean = Beans.getReference(beanManager, clazz);
			bean = getReference(beanManager, clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			bean = Beans.getReference(beanManager, beanName);
			bean = getReference(beanManager, beanName);
		}
		
		@SuppressWarnings("rawtypes")
		InjectionTarget it = getInjectionTarget(bean);
		it.produce(creationalContext);
		it.inject(bean, creationalContext);
		it.postConstruct(bean);
		return bean;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.object.BeanFactory#destroyBean(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void destroyBean(Object bean) {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		InjectionTarget it = getInjectionTarget(bean);
		it.preDestroy(bean);
		it.dispose(bean);
		injectionTargetCache.remove(bean.getClass());
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getReference(BeanManager beanManager, Class<T> clazz) {
		Iterator<Bean<?>> iter = beanManager.getBeans(clazz).iterator();
        if (!iter.hasNext()) {
            throw new IllegalStateException("CDI BeanManager cannot find an instance of requested type " + clazz.getName());
        }
		Bean<T> bean = (Bean<T>) iter.next();
		creationalContext = beanManager.createCreationalContext(bean);
        T reference = (T) beanManager.getReference(bean, clazz, creationalContext);
        return reference;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getReference(BeanManager beanManager, String beanElName) {
		Iterator<Bean<?>> iter = beanManager.getBeans(beanElName).iterator();
        if (!iter.hasNext()) {
            throw new IllegalStateException("CDI BeanManager cannot find an instance of requested EL name " + beanElName);
        }
        Bean<T> bean = (Bean<T>) iter.next();
        creationalContext = beanManager.createCreationalContext(bean);
        Type type = (Type) bean.getTypes().iterator().next();
        return (T) beanManager.getReference(bean, type, creationalContext);
	}
	
	protected InjectionTarget<?> getInjectionTarget(Object bean) {
		Class<?> clazz = bean.getClass();
		InjectionTarget<?> target = injectionTargetCache.get(clazz);
		if (target == null) {
			target = beanManager.createInjectionTarget(beanManager.createAnnotatedType(clazz));
			injectionTargetCache.put(clazz, target);
		}
		
		return target;
	}
}
