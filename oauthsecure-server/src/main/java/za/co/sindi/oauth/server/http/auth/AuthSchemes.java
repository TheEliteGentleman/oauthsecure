/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * Standard authentication schemes supported by OAuth.
 * 
 * @author Bienfait Sindi
 * @since 09 January 2016
 *
 */
public final class AuthSchemes {

	/**
     * Basic authentication scheme as defined in RFC 2617 (considered inherently
     * insecure, but most widely supported).
     */
	public static final String BASIC = "Basic";
	
	/**
     * OAuth 2.0 Bearer Token authentication scheme as defined in RFC 6750. 
     */
	public static final String BEARER = "Bearer";
	
	/**
     * OAuth authentication scheme as defined in RFC 5849. 
     */
	public static final String OAUTH = "OAuth";

	/**
	 * 
	 */
	private AuthSchemes() {
		super();
		// TODO Auto-generated constructor stub
		throw new AssertionError("Private Constructor.");
	}
}
