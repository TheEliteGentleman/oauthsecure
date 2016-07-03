/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * @author Bienfait Sindi
 * @since 08 January 2016
 *
 */
public interface AuthenticationSchemeBuilder<T extends AuthenticationScheme> {

	public T build();
}
