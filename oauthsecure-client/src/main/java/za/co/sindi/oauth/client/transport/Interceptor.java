/**
 * 
 */
package za.co.sindi.oauth.client.transport;

/**
 * @author Buhake Sindi
 * @since 17 February 2012
 *
 */
public interface Interceptor<T> {

	/**
	 * This method accept an object being intercepted.
	 * @param object
	 */
	public void accept(final T object);
}
