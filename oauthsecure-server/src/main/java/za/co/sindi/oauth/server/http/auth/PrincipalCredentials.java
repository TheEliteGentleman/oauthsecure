/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import java.security.Principal;

/**
 * @author Bienfait Sindi
 * @since 17 January 2016
 *
 */
public interface PrincipalCredentials extends Credentials {

	public Principal getUserPrincipal();
}
