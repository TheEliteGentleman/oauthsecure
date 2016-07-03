/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public interface AuthenticationSchemeBuilderFactory<C extends Credentials, CR extends CredentialsAuthenticationScheme<C>, CH extends ChallengeAuthenticationScheme> {

	public CredentialsAuthenticationSchemeBuilder<C, CR> createCredentialsAuthenticationBuilder(final String requestHeaderName);
	public ChallengeAuthenticationSchemeBuilder<CH> createChallengeAuthenticationBuilder(final int responseStatusCode, final String responseHeaderName);
}
