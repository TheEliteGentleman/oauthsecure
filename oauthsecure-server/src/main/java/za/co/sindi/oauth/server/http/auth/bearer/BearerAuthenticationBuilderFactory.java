/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.bearer;

import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory;
import za.co.sindi.oauth.server.http.auth.ChallengeAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.TokenCredential;
import za.co.sindi.oauth.server.http.auth.bearer.BearerChallengeAuthenticationScheme.BearerChallengeAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.bearer.BearerCredentialsAuthenticationScheme.BearerCredentialsAuthenticationSchemeBuilder;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public class BearerAuthenticationBuilderFactory implements AuthenticationSchemeBuilderFactory<TokenCredential, BearerCredentialsAuthenticationScheme, BearerChallengeAuthenticationScheme> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory#createCredentialsAuthenticationBuilder(java.lang.String)
	 */
	@Override
	public CredentialsAuthenticationSchemeBuilder<TokenCredential, BearerCredentialsAuthenticationScheme> createCredentialsAuthenticationBuilder(String requestHeaderName) {
		// TODO Auto-generated method stub
		return new BearerCredentialsAuthenticationSchemeBuilder(requestHeaderName);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory#createChallengeAuthenticationBuilder(int, java.lang.String)
	 */
	@Override
	public ChallengeAuthenticationSchemeBuilder<BearerChallengeAuthenticationScheme> createChallengeAuthenticationBuilder(int responseStatusCode, String responseHeaderName) {
		// TODO Auto-generated method stub
		return new BearerChallengeAuthenticationSchemeBuilder(responseHeaderName);
	}
}
