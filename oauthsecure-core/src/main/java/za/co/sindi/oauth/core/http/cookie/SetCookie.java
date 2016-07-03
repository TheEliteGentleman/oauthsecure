/**
 * 
 */
package za.co.sindi.oauth.core.http.cookie;

import za.co.sindi.oauth.core.http.Cookie;

/**
 * @author Buhake Sindi
 * @since 21 March 2012
 *
 */
public interface SetCookie extends Cookie {

	public void setName(String name);
	public void setValue(String value);
	public void setComment(String comment);
	public void setDomain(String domain);
	public void setMaxAge(int maxAge);
	public void setPath(String path);
	public void setSecure(boolean secure);
	public void setVersion(int version);
}
