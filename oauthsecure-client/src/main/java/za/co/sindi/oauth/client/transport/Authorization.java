/**
 * 
 */
package za.co.sindi.oauth.client.transport;

import za.co.sindi.oauth.client.transport.exception.AuthorizationException;

/**
 * @author Bienfait Sindi
 * @since 08 March 2016
 *
 */
public interface Authorization<T extends Request> {

	public void authenticate(T request) throws AuthorizationException;
}
