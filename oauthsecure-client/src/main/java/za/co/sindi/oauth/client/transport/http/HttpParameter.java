/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public interface HttpParameter {

	public HttpParameter addValue(String value);
	public String getName();
	public String[] getValues();
}
