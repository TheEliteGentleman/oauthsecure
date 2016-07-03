/**
 * 
 */
package za.co.sindi.oauth.core.http.cookie.impl;

import za.co.sindi.oauth.core.http.cookie.SetCookie2;

/**
 * <a href="http://tools.ietf.org/html/rfc2965">RFC 2965</a> Set-Cookie 2.
 * 
 * @author Buhake Sindi
 * @since 21 March 2012
 *
 */
public class HttpSetCookie2 extends HttpSetCookie implements SetCookie2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6198178175872657747L;
	private String commentURL;
	private boolean discard;
	private int[] port;
	
	/**
	 * @param name
	 * @param value
	 */
	public HttpSetCookie2(String name, String value) {
		super(name, value);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie2#getCommentURL()
	 */
	@Override
	public String getCommentURL() {
		// TODO Auto-generated method stub
		return commentURL;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie2#setCommentURL(java.lang.String)
	 */
	@Override
	public void setCommentURL(String commentURL) {
		// TODO Auto-generated method stub
		this.commentURL = commentURL;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie2#isDiscard()
	 */
	@Override
	public boolean isDiscard() {
		// TODO Auto-generated method stub
		return discard;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie2#setDiscard(boolean)
	 */
	@Override
	public void setDiscard(boolean discard) {
		// TODO Auto-generated method stub
		this.discard = discard;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie2#getPort()
	 */
	@Override
	public int[] getPort() {
		// TODO Auto-generated method stub
		return port;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie2#setPort(int[])
	 */
	@Override
	public void setPort(int[] port) {
		// TODO Auto-generated method stub
		this.port = port;
	}
}
