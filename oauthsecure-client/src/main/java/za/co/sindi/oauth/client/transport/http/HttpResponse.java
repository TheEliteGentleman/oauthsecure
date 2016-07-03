/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import za.co.sindi.oauth.client.transport.Response;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public interface HttpResponse extends Response, MessageHeader {
	
	public int getMajorVersion();
	public int getMinorVersion();
	public int getStatusCode();
	public String getReasonPhrase();
}
