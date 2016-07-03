/**
 * 
 */
package za.co.sindi.oauth.core.enums;

/**
 * The OAuth 2 error codes as stipulated on Section 5.2 - Error Response on RFC 6749.
 * 
 * @author Bienfait Sindi
 * @since 02 October 2014
 *
 */
public enum OAuth2Error {

	INVALID_REQUEST("invalid_request"),
	INVALID_CLIENT("invalid_client"),
	ACCESS_DENIED("access_denied"),
	INVALID_GRANT("invalid_grant"),
	UNAUTHORIZED_CLIENT("unauthorized_client"),
	UNSUPPORTED_GRANT_TYPE("unsupported_grant_type"),
	UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type"),
	INVALID_SCOPE("invalid_scope"),
	SERVER_ERROR("server_error"),
	TEMPORARILY_UNAVAILABLE("temporarily_unavailable")
	;
	private final String error;

	/**
	 * @param error
	 */
	private OAuth2Error(final String error) {
		this.error = error;
	}
	
	public static OAuth2Error of(String error) {
		for (OAuth2Error oauth2Error : values()) {
			if (oauth2Error.error.equals(error)) {
				return oauth2Error;
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return error;
	}	
}
