/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.entity;

import java.io.UnsupportedEncodingException;

import za.co.sindi.oauth.client.transport.http.AbstractHttpEntity;
import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public class PlainTextHttpEntity extends AbstractHttpEntity {

	/**
	 * Default Constructs with default <code>charset</code> of <code>ISO-8859-1</code>.
	 * 
	 * @param s
	 * @throws UnsupportedEncodingException
	 */
	public PlainTextHttpEntity(String s) throws UnsupportedEncodingException {
		this(s, HttpConstants.DEFAULT_CHARSET);
	}
	
	/**
	 * @param s
	 * @param charset
	 * @throws UnsupportedEncodingException 
	 */
	public PlainTextHttpEntity(String s, String charset) throws UnsupportedEncodingException {
		super("text/plain");
		// TODO Auto-generated constructor stub
		if (s == null) {
			throw new IllegalArgumentException("value is null.");
		}
		
		setCharset(charset);
		setContent(s.getBytes(charset));
	}
}
