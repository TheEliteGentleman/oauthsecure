/**
 * 
 */
package za.co.sindi.oauth.server.object;

/**
 * @author Bienfait Sindi
 * @since 20 October 2014
 *
 */
public interface BeanFactory {

	public Object getBean(String beanName);
	public void destroyBean(Object bean);
}
