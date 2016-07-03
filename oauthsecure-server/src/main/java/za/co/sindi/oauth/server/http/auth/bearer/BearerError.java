/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.bearer;

/**
 * @author Bienfait Sindi
 * @since 08 February 2016
 *
 */
public enum BearerError {
	INVALID_REQUEST(400, "invalid_request")
	,INVALID_TOKEN(401, "invalid_token")
	,INSUFFICIENT_SCOPE(403, "insufficient_scope")
	;
	private final int statusCode;
	private final String type;
	
	/**
	 * @param statusCode
	 * @param type
	 */
	private BearerError(int statusCode, String type) {
		this.statusCode = statusCode;
		this.type = type;
	}
	
	public static BearerError of(String error) {
		for (BearerError bearerError : values()) {
			if (bearerError.type.equals(error)) {
				return bearerError;
			}
		}
		
		return null;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type;
	}
}
