/**
 * 
 */
package za.co.sindi.oauth.server.security;

import java.security.Principal;

import za.co.sindi.oauth.server.http.auth.Credentials;

/**
 * @author Bienfait Sindi
 * @since 14 January 2016
 *
 */
public interface AuthenticationManager {

	public Principal authenticate(Credentials credentials);
}
