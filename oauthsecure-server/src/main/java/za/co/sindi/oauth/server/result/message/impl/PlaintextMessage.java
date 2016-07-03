/**
 * 
 */
package za.co.sindi.oauth.server.result.message.impl;

import java.io.UnsupportedEncodingException;

import za.co.sindi.oauth.server.result.message.AbstractMessage;

/**
 * @author Buhake Sindi
 * @since 14 October 2011
 *
 */
public class PlaintextMessage extends AbstractMessage {

	/**
	 * @param contentType
	 * @param content
	 * @throws UnsupportedEncodingException 
	 */
	public PlaintextMessage(final String content, String charset) throws UnsupportedEncodingException {
		super();
		if (content == null) {
			throw new IllegalArgumentException("Content string must not be null.");
		}
		
		if (charset == null || charset.isEmpty()) {
			charset = DEFAULT_HTTP_CHARSET;
		}
		
		setContent(content.getBytes(charset));
		setContentType("text/plain; charset=" + charset);
	}
}
