/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.request;

import java.net.URI;

import za.co.sindi.oauth.client.transport.http.EntityBasedHttpRequest;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public class PutHttpRequest extends EntityBasedHttpRequest {

	/**
	 * @param uri
	 */
	public PutHttpRequest(URI uri) {
		super("PUT", uri);
		// TODO Auto-generated constructor stub
	}
}
