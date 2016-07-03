/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.response;

import java.io.InputStream;

import za.co.sindi.oauth.client.transport.http.AbstractHttpResponse;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public class BasicHttpResponse extends AbstractHttpResponse {

	/**
	 * @param minorVersion
	 * @param statusCode
	 * @param reasonPhrase
	 * @param responseStream
	 * 
	 * Construct setting HTTP Major version to 1.
	 */
	public BasicHttpResponse(int minorVersion, int statusCode, String reasonPhrase, InputStream responseStream) {
		this(1, minorVersion, statusCode, reasonPhrase, responseStream);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param majorVersion
	 * @param minorVersion
	 * @param statusCode
	 * @param reasonPhrase
	 * @param responseStream
	 */
	public BasicHttpResponse(int majorVersion, int minorVersion, int statusCode, String reasonPhrase, InputStream responseStream) {
		super(majorVersion, minorVersion, statusCode, reasonPhrase, responseStream);
		// TODO Auto-generated constructor stub
	}
}
