/**
 * 
 */
package za.co.sindi.oauth.client.transport.interceptor;

import za.co.sindi.oauth.client.transport.Interceptor;
import za.co.sindi.oauth.client.transport.Response;

/**
 * @author Buhake Sindi
 * @since 17 February 2012
 *
 */
public interface ResponseInterceptor<T extends Response> extends Interceptor<T> {

}
