/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import java.io.IOException;

import za.co.sindi.oauth.server.context.http.HttpResponseContext;

/**
 * @author Bienfait Sindi
 * @since 09 Feburary 2016
 *
 */
public interface ChallengeAuthenticationScheme extends AuthenticationScheme {

	public void sendAuthenticateResponse(HttpResponseContext response) throws IOException;
}
