/**
 * 
 */
package za.co.sindi.oauth.server.result.message.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import za.co.sindi.codec.StringCodec;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.encoding.WWWFormURLEncodingCodec;
import za.co.sindi.oauth.server.result.message.AbstractMessage;

/**
 * @author Buhake Sindi
 * @since 14 October 2011
 *
 */
public class URLEncodedMessage extends AbstractMessage {

	/**
	 * 
	 * @param parameters
	 * @param charset
	 * @throws UnsupportedEncodingException
	 * @throws EncodingException 
	 */
	public URLEncodedMessage(final Map<String, String[]> parameters, String charset) throws UnsupportedEncodingException, EncodingException {
		// TODO Auto-generated constructor stub
		super();
		
		if (parameters == null) {
			throw new IllegalArgumentException("Parameters must not be null.");
		}
		
		StringCodec codec = new WWWFormURLEncodingCodec(charset);
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (sb.length() > 0) {
					sb.append("&");
				}
				
				sb.append(codec.encode(entry.getKey())).append("=")
				  .append(codec.encode(value));
			}
		}

		if (charset == null || charset.isEmpty()) {
			charset = DEFAULT_HTTP_CHARSET;
		}
		
		setContent(sb.toString().getBytes(charset));
		setContentType("application/x-www-form-urlencoded; charset=" +  charset);
	}
}
