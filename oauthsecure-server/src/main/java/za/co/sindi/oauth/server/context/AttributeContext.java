/**
 * 
 */
package za.co.sindi.oauth.server.context;

/**
 * @author Buhake Sindi
 * @since 13 April 2012
 *
 */
public interface AttributeContext {

	public Object getAttribute(String name);
	public String[] getAttributeNames();
	public void removeAttribute(String name);
	public void setAttribute(String name, Object object);
}
