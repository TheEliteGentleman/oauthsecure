/**
 * 
 */
package za.co.sindi.oauth.server.context;

/**
 * @author Buhake Sindi
 * @since 17 April 2012
 *
 */
public interface SessionContext extends Context, AttributeContext {

	public String getId();
	public long getCreationTime();
	public long getLastAccessedTime();
	public int getMaxInactiveInterval();
	public boolean isNew();
	public void invalidate();
	public void setMaxInactiveInterval(int interval);
	
}
