/**
 * 
 */
package za.co.sindi.oauth.client;

import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.transport.Response;

/**
 * @author Buhake Sindi
 * @since 07 February 2012
 *
 */
public interface ResponseHandler<T> {

	/**
	 * The core method that handles any {@link Response}.
	 * @param response
	 * @return
	 * @throws OAuthResponseException
	 */
	public T handleResponse(Response response) throws OAuthResponseException;
}
