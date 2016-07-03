/**
 * 
 */
package za.co.sindi.oauth.core.http;

import java.io.Serializable;

/**
 * @author Buhake Sindi
 * @since 21 March 2012
 *
 */
public interface Cookie extends Serializable {
	
	public String getName();
	public String getValue();
	public String getComment();
	public String getDomain();
	public int getMaxAge();
	public String getPath();
	public boolean isSecure();
	public int getVersion();
}
