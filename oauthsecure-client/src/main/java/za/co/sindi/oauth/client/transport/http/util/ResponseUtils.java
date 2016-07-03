/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.util;

import za.co.sindi.oauth.client.transport.http.Header;
import za.co.sindi.oauth.client.transport.http.HttpResponse;
import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * @author Buhake Sindi
 * @since 10 February 2012
 *
 */
public class ResponseUtils {

	private static final String CHARSET_TOKEN = "charset="; // "; charset=";
	private static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 
	 */
	private ResponseUtils() {
		// TODO Auto-generated constructor stub
	}

	public static String getCharset(HttpResponse response) {
		if (response == null) {
			return null;
		}
		
		Header header = response.getHeader(HttpConstants.HEADER_CONTENT_TYPE);
		if (header == null) {
			return null;
		}
		
		String value = header.getValue();
		int charsetPos = value.indexOf(CHARSET_TOKEN); 
		if (charsetPos == -1) {
			return DEFAULT_CHARSET;
		}
		
		return value.substring(charsetPos + CHARSET_TOKEN.length());
	}
	
	public static final String getContentType(HttpResponse response) {
		if (response == null) {
			return null;
		}
		
		Header header = response.getHeader(HttpConstants.HEADER_CONTENT_TYPE);
		if (header == null) {
			return null;
		}
		
		String value = header.getValue();
		int semicolonPos = value.indexOf(";", 1);
		if (semicolonPos == -1) {
			return value;
		}
		
		return value.substring(0, semicolonPos);
	}
}
