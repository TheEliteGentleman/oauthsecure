/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.basic;

import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory;
import za.co.sindi.oauth.server.http.auth.ChallengeAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.UsernamePasswordCredentials;
import za.co.sindi.oauth.server.http.auth.basic.BasicChallengeAuthenticationScheme.BasicChallengeAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.basic.BasicCredentialsAuthenticationScheme.BasicCredentialsAuthenticationSchemeBuilder;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public class BasicAuthenticationBuilderFactory implements AuthenticationSchemeBuilderFactory<UsernamePasswordCredentials, BasicCredentialsAuthenticationScheme, BasicChallengeAuthenticationScheme> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory#createCredentialsAuthenticationBuilder(java.lang.String)
	 */
	@Override
	public CredentialsAuthenticationSchemeBuilder<UsernamePasswordCredentials, BasicCredentialsAuthenticationScheme> createCredentialsAuthenticationBuilder(String requestHeaderName) {
		// TODO Auto-generated method stub
		return new BasicCredentialsAuthenticationSchemeBuilder(requestHeaderName);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory#createChallengeAuthenticationBuilder(int, java.lang.String)
	 */
	@Override
	public ChallengeAuthenticationSchemeBuilder<BasicChallengeAuthenticationScheme> createChallengeAuthenticationBuilder(int responseStatusCode, String responseHeaderName) {
		// TODO Auto-generated method stub
		return new BasicChallengeAuthenticationSchemeBuilder(responseStatusCode, responseHeaderName);
	}
}
