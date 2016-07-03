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
public interface SetCookie2 extends Cookie {

	public String getCommentURL();
	public void setCommentURL(String commentURL);
	
	public boolean isDiscard();
	public void setDiscard(boolean discard);
	
	public int[] getPort();
	public void setPort(int[] port);
}
