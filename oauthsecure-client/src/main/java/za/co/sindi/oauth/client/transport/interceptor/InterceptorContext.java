/**
 * 
 */
package za.co.sindi.oauth.client.transport.interceptor;

import za.co.sindi.oauth.client.transport.Interceptor;

/**
 * @author Buhake Sindi
 * @since 14 March 2012
 *
 */
public interface InterceptorContext<T> {

	public void addInterceptor(Interceptor<T> interceptor);
	public void removeInterceptor(Interceptor<T> interceptor);
	public void clearInterceptors();
}
