/**
 * 
 */
package za.co.sindi.oauth.server.context;

/**
 * @author Buhake Sindi
 * @since 14 April 2012
 *
 */
public interface PropertyContext {

	public String[] getProperties(String name);
	public String getProperty(String name);
	public String[] getPropertyNames();
}
