/**
 * 
 */
package za.co.sindi.oauth.server.util;

/**
 * @author Buhake Sindi
 * @since 07 September 2011
 *
 */
public final class ApplicationUtils {

	private ApplicationUtils(){}
	
	public static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		if (classLoader == null) {
			classLoader = ApplicationUtils.class.getClassLoader();
		}
		
		return classLoader;
	}
	
	public static <T> Class<T> applicationClass(String className) throws ClassNotFoundException {
		return applicationClass(className, null);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> applicationClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
		if (classLoader == null) {
			classLoader = getClassLoader();
		}
		
		return (Class<T>) classLoader.loadClass(className);
	}
	
	public static <T> T applicationInstance(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return applicationInstance(className, null);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T applicationInstance(String className, ClassLoader classLoader) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (T)applicationClass(className, classLoader).newInstance();
	}
}
