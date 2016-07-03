/**
 * 
 */
package za.co.sindi.oauth.server.spi.oauth2.clientreg;

/**
 * @author Bienfait Sindi
 * @since 28 March 2016
 *
 */
public class Constants {

	private Constants() {
		throw new AssertionError("Private Constructor.");
	}
	
	//Client Metadata attributes
	public static final String CM_REDIRECT_URIS = "redirect_uris";
	public static final String CM_TOKEN_ENDPOINT_AUTH_METHOD = "token_endpoint_auth_method";
	public static final String CM_GRANT_TYPES = "grant_types";
	public static final String CM_RESPONSE_TYPES = "response_types";
	public static final String CM_CLIENT_NAME = "client_name";
	public static final String CM_CLIENT_URI = "client_uri";
	public static final String CM_LOGO_URI = "logo_uri";
	public static final String CM_SCOPE = "scope";
	public static final String CM_CONTACTS = "contacts";
	public static final String CM_TOS_URI = "tos_uri";
	public static final String CM_POLICY_URI = "policy_uri";
	public static final String CM_JWKS_URI = "jwks_uri";
	public static final String CM_JWKS = "jwks";
	public static final String CM_SOFTWARE_ID = "software_id";
	public static final String CM_SOFTWARE_VERSION = "software_version";
}
