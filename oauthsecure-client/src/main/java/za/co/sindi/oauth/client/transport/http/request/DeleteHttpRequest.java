/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.request;

import java.net.URI;

import za.co.sindi.oauth.client.transport.http.UriQueryParameterHttpRequest;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public class DeleteHttpRequest extends UriQueryParameterHttpRequest {

	/**
	 * @param uri
	 */
	public DeleteHttpRequest(URI uri) {
		super("DELETE", uri);
		// TODO Auto-generated constructor stub
	}
}
