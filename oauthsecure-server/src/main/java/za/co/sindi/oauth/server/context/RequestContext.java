/**
 * 
 */
package za.co.sindi.oauth.server.context;

import java.util.Locale;


/**
 * @author Buhake Sindi
 * @since 25 March 2012
 *
 */
public interface RequestContext extends Context {

	public Locale getLocale();
	public Locale[] getLocales();
	public String getScheme();
	public boolean isSecure();
	public String getServerName();
	public int getServerPort();
	public SessionContext getSessionContext();
	public SessionContext getSessionContext(boolean create);
}
