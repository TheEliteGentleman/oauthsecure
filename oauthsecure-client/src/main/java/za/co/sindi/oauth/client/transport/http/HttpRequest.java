/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.net.URI;

import za.co.sindi.oauth.client.transport.Request;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public interface HttpRequest extends Request, MessageHeader {

	public URI getURI();
}
