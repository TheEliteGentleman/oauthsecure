/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * @author Bienfait Sindi
 * @since 11 January 2016
 *
 */
public interface PasswordCredentials extends Credentials {

	public String getPassword();
}
