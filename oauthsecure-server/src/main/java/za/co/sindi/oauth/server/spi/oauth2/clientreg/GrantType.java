/**
 * 
 */
package za.co.sindi.oauth.server.spi.oauth2.clientreg;

/**
 * @author Bienfait Sindi
 * @since 28 Marrch 2016
 *
 */
public final class GrantType {
	
	public static final GrantType AUTHORIZATION_CODE = new GrantType("authorization_code");
	
	public static final GrantType CLIENT_CREDENTIALS = new GrantType("client_credentials");
	
	public static final GrantType IMPLICIT = new GrantType("implicit");
	
	public static final GrantType PASSWORD = new GrantType("password");
	
	public static final GrantType REFRESH_TOKEN = new GrantType("refresh_token");
	
	public static final GrantType JWT_BEARER = new GrantType("urn:ietf:params:oauth:grant-type:jwt-bearer");
	
	public static final GrantType SAML2_BEARER = new GrantType("urn:ietf:params:oauth:grant-type:saml2-bearer");
	
	private final String code;

	/**
	 * @param code
	 */
	private GrantType(final String code) {
		this.code = code;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrantType other = (GrantType) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code;
	}
}
