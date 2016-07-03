/**
 * 
 */
package za.co.sindi.oauth.server.context;



/**
 * @author Buhake Sindi
 * @since 15 April 2012
 *
 */
public interface ApplicationContext extends Context {

	public String getMimeType(String path);
	public String getServerInfo();
	public void log(String message);
	public void log(String message, Throwable throwable);
	public Object getContext();
}
