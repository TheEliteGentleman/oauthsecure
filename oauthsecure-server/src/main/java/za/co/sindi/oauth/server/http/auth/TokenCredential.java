/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import java.security.Principal;

/**
 * @author Bienfait Sindi
 * @since 08 February 2016
 *
 */
public class TokenCredential implements Credentials, PrincipalCredentials {

	private TokenPrincipal tokenPrincipal;
	
	/**
	 * @param tokenPrincipal
	 */
	public TokenCredential(TokenPrincipal tokenPrincipal) {
		super();
		if (tokenPrincipal == null) {
			throw new IllegalArgumentException("No token principal was specified.");
		}
		this.tokenPrincipal = tokenPrincipal;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.PrincipalCredentials#getUserPrincipal()
	 */
	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return tokenPrincipal;
	}
}
