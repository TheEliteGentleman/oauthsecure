/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.exception.HttpException;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public interface CredentialsAuthenticationScheme<T extends Credentials> extends AuthenticationScheme {

	public T extractCredentials(HttpRequestContext request) throws HttpException;
}
