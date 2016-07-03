/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * @author Bienfait Sindi
 * @since 08 January 2016
 *
 */
public interface AuthenticationScheme {

	public String getSchemeName();
//	public T extractCredentials(HttpRequestContext request) throws HttpException;
//	public void sendAuthenticateResponse(HttpResponseContext response) throws IOException;
}
