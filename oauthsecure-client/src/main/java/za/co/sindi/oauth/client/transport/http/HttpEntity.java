/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public interface HttpEntity {

	public String getCharset();
	public String getContentType();
	public int getContentLength();
	public byte[] getContent();
	public void writeTo(final OutputStream output) throws IOException;
}
