/**
 * 
 */
package za.co.sindi.oauth.server.context;

/**
 * @author Buhake Sindi
 * @since 14 April 2012
 *
 */
public interface HeaderContext {

	public boolean containsHeader(String name);
	public String getHeader(String name);
	public String[] getHeaderNames();
	public String[] getHeaders(String name);
}
