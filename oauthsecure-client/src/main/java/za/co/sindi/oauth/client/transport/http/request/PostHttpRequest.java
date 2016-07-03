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
public class PostHttpRequest extends EntityBasedHttpRequest {

	/**
	 * @param uri
	 */
	public PostHttpRequest(URI uri) {
		super("POST", uri);
		// TODO Auto-generated constructor stub
	}
}
