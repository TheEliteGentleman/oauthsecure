/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.util;

/**
 * @author Buhake Sindi
 * @since 09 March 2012
 *
 */
public final class HttpUtils {
	
	public static final int CR = 13; // carriage return
    public static final int LF = 10; // linefeed
    public static final int SP = 32; // space
    public static final int HT = 9;  // horizontal-tab

	private HttpUtils() {
	}
	
	public static boolean isWhitespace(char c) {
		return (c == SP || c == HT || c == CR || c == LF);
	}
}
