/**
 * 
 */
package za.co.sindi.oauth.server.spi.oauth2.clientreg;

/**
 * @author Bienfait Sindi
 * @since 28 March 2016
 *
 */
public enum TokenEndpointAuthMethod {
	NONE("none")
	,CLIENT_SECRET_BASIC("client_secret_basic")
	,CLIENT_SECRET_POST("client_secret_post`")
	;
	private final String type;

	/**
	 * @param type
	 */
	private TokenEndpointAuthMethod(String type) {
		this.type = type;
	}
	
	public static TokenEndpointAuthMethod of(final String type) {
		for (TokenEndpointAuthMethod authMethod : values()) {
			if (authMethod.type.equals(type)) {
				return authMethod;
			}
		}
		
		throw new IllegalArgumentException("Unknown token_endpoint_auth_method '" + type + "'.");
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
