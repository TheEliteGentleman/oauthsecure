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
public class TokenPrincipal implements Principal {

	private final String token;
	
	/**
	 * @param token
	 */
	public TokenPrincipal(String token) {
		super();
		if (token == null || token.isEmpty()) {
			throw new IllegalArgumentException("No token specified.");
		}
		
		this.token = token;
	}

	/* (non-Javadoc)
	 * @see java.security.Principal#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return token;
	}
}
