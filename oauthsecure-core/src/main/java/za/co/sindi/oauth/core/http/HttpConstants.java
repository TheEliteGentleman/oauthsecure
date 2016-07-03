/**
 * 
 */
package za.co.sindi.oauth.core.http;

/**
 * @author Buhake Sindi
 * @since 15 February 2012
 *
 */
public final class HttpConstants {
	
	private HttpConstants() {
		throw new AssertionError("Private Constructor.");
	}
	
	//Status codes
	public static final int SC_OK = 200;
	public static final int SC_SEE_OTHER = 303;
    public static final int SC_BAD_REQUEST = 400;
    public static final int SC_UNAUTHORIZED = 401;
    public static final int SC_PROXY_AUTHENTICATION_REQUIRED = 407;

	//HTTP Methods
    public static final String METHOD_CONNECT = "CONNECT";
    public static final String METHOD_DELETE = "DELETE";
	public static final String METHOD_GET = "GET";
	public static final String METHOD_HEAD = "HEAD";
	public static final String METHOD_OPTIONS = "OPTIONS";
	public static final String METHOD_PATCH = "PATCH";
	public static final String METHOD_POST = "POST";
	public static final String METHOD_PUT = "PUT";
	public static final String METHOD_TRACE = "TRACE";
	
	//Authorization headers
	public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final String HEADER_PROXY_AUTHENTICATE = "Proxy-Authenticate";
	public static final String HEADER_PROXY_AUTHORIZATION = "Proxy-Authorization";
	public static final String HEADER_WWW_AUTHENTICATE = "WWW-Authenticate";
	
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_CACHE_CONTROL = "Cache-Control";
	public static final String HEADER_HOST = "Host";
	public static final String HEADER_LOCATION = "Location";
	public static final String HEADER_PRAGMA = "Pragma";
	
	//For Authorization
	public static final String REALM = "realm";
	public static final String CHARSET_UTF8 ="UTF-8";
	public static final String CHARSET_ISO_8859_1 = "ISO-8859-1";
	public static final String DEFAULT_CHARSET = CHARSET_ISO_8859_1;
}
