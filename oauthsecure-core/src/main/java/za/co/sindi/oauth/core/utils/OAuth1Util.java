/**
 * 
 */
package za.co.sindi.oauth.core.utils;

import za.co.sindi.codec.exception.DecodingException;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.encoding.PercentEncodingCodec;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public class OAuth1Util {

	/**
	 * 
	 */
	private OAuth1Util() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Percent encode a string (with UTF-8 encoding).
	 * 
	 * @param s
	 * @return
	 * @throws RuntimeException
	 */
	@Deprecated
	public static String encode(String s) {
		try {
			return new PercentEncodingCodec().encode(s);
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Percent decode a string (with UTF-8 encoding).
	 * 
	 * @param s
	 * @return
	 * @throws RuntimeException
	 */
	@Deprecated
	public static String decode(String s) {
		try {
			return new PercentEncodingCodec().decode(s);
		} catch (DecodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Returns a timestamp according to RFC 5849, paragraph 3.3.
	 * 
	 * @return
	 */
	public static long getTimestamp() {
		return System.currentTimeMillis() / 1000;
	}
}
