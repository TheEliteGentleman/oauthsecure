/**
 * 
 */
package za.co.sindi.oauth.core.http.cookie.impl;

import za.co.sindi.oauth.core.http.cookie.SetCookie;

/**
 * <a href="http://tools.ietf.org/html/rfc2109">RFC 2109</a> Set-Cookie.
 * 
 * @author Buhake Sindi
 * @since 21 March 2012
 *
 */
public class HttpSetCookie implements SetCookie {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3101702326732773343L;
	private String name;
	private String value;
	private String comment;
	private String domain;
	private int maxAge;
	private String path;
	private boolean secure;
	private int version;
	
	/**
	 * @param name
	 * @param value
	 */
	public HttpSetCookie(String name, String value) {
		super();
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name may not be null or empty.");
		}
		
		this.name = name;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Cookie#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Cookie#getValue()
	 */
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Cookie#getComment()
	 */
	@Override
	public String getComment() {
		// TODO Auto-generated method stub
		return comment;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Cookie#getDomain()
	 */
	@Override
	public String getDomain() {
		// TODO Auto-generated method stub
		return domain;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Cookie#getMaxAge()
	 */
	@Override
	public int getMaxAge() {
		// TODO Auto-generated method stub
		return maxAge;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Cookie#getPath()
	 */
	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Cookie#isSecure()
	 */
	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return secure;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Cookie#getVersion()
	 */
	@Override
	public int getVersion() {
		// TODO Auto-generated method stub
		return version;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie#setComment(java.lang.String)
	 */
	@Override
	public void setComment(String comment) {
		// TODO Auto-generated method stub
		this.comment = comment;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie#setDomain(java.lang.String)
	 */
	@Override
	public void setDomain(String domain) {
		// TODO Auto-generated method stub
		this.domain = domain;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie#setMaxAge(int)
	 */
	@Override
	public void setMaxAge(int maxAge) {
		// TODO Auto-generated method stub
		this.maxAge = maxAge;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie#setPath(java.lang.String)
	 */
	@Override
	public void setPath(String path) {
		// TODO Auto-generated method stub
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie#setSecure(boolean)
	 */
	@Override
	public void setSecure(boolean secure) {
		// TODO Auto-generated method stub
		this.secure = secure;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.cookie.SetCookie#setVersion(int)
	 */
	@Override
	public void setVersion(int version) {
		// TODO Auto-generated method stub
		this.version = version;
	}
}
