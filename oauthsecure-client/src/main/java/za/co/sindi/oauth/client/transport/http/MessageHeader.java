/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public interface MessageHeader {

	public void addHeader(Header header);
	public boolean containsHeader(String name);
	public Header getHeader(String name);
	public void removeHeader(Header header);
	public Header[] getHeaders();
}
