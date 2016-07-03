/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.oauth;

import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory;
import za.co.sindi.oauth.server.http.auth.ChallengeAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.OAuth1Credentials;
import za.co.sindi.oauth.server.http.auth.oauth.OAuthChallengeAuthenticationScheme.OAuthChallengeAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.oauth.OAuthCredentialsAuthenticationScheme.OAuthCredentialsAuthenticationSchemeBuilder;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public class OAuthAuthenticationBuilderFactory implements AuthenticationSchemeBuilderFactory<OAuth1Credentials, OAuthCredentialsAuthenticationScheme, OAuthChallengeAuthenticationScheme> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory#createCredentialsAuthenticationBuilder(java.lang.String)
	 */
	@Override
	public CredentialsAuthenticationSchemeBuilder<OAuth1Credentials, OAuthCredentialsAuthenticationScheme> createCredentialsAuthenticationBuilder(String requestHeaderName) {
		// TODO Auto-generated method stub
		return new OAuthCredentialsAuthenticationSchemeBuilder(requestHeaderName);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory#createChallengeAuthenticationBuilder(int, java.lang.String)
	 */
	@Override
	public ChallengeAuthenticationSchemeBuilder<OAuthChallengeAuthenticationScheme> createChallengeAuthenticationBuilder(int responseStatusCode, String responseHeaderName) {
		// TODO Auto-generated method stub
		return new OAuthChallengeAuthenticationSchemeBuilder(responseStatusCode, responseHeaderName);
	}
}
