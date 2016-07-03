/**
 * 
 */
package za.co.sindi.oauth.client.transport;

import java.io.InputStream;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public interface Response {

	public InputStream getResponseStream();
}
