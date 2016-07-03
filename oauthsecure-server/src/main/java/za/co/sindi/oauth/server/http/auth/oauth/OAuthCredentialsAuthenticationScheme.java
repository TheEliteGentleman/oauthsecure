/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.oauth;

import java.util.Map;
import java.util.Map.Entry;

import za.co.sindi.oauth.core.encoding.PercentEncodingCodec;
import za.co.sindi.oauth.core.http.HttpConstants;
import za.co.sindi.oauth.core.http.HttpHeaderParameterCodec;
import za.co.sindi.oauth.core.utils.OAuth1Constants;
import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.exception.HttpException;
import za.co.sindi.oauth.server.exception.http.MalformedChallengeException;
import za.co.sindi.oauth.server.http.auth.AbstractCredentialsAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.AuthSchemes;
import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.OAuth1AuthenticatedCredentials;
import za.co.sindi.oauth.server.http.auth.OAuth1Credentials;
import za.co.sindi.oauth.server.http.auth.OAuth1TemporaryCredentials;
import za.co.sindi.oauth.server.http.auth.OAuth1TokenCredentials;
import za.co.sindi.oauth.server.http.auth.OAuthSignatureMethod;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public class OAuthCredentialsAuthenticationScheme extends AbstractCredentialsAuthenticationScheme<OAuth1Credentials> implements CredentialsAuthenticationScheme<OAuth1Credentials> {

	/**
	 * @param builder
	 */
	private OAuthCredentialsAuthenticationScheme(OAuthCredentialsAuthenticationSchemeBuilder builder) {
		super(AuthSchemes.OAUTH, builder.getRequestHeaderName());
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.AbstractCredentialsAuthenticationScheme#extractCredentials(za.co.sindi.oauth.server.context.http.HttpRequestContext, java.lang.String)
	 */
	@Override
	protected OAuth1Credentials extractCredentials(HttpRequestContext request, String authorizationString) throws HttpException {
		// TODO Auto-generated method stub
		Map<String, String[]> oauthParameters = new HttpHeaderParameterCodec(new PercentEncodingCodec()).decode(authorizationString);
		if (oauthParameters == null || oauthParameters.isEmpty()) {
			throw new MalformedChallengeException("No OAuth parameters exists (credentials found = " + authorizationString + ").");
		}
		
		//Validate, if any. Throw exceptions when an issue is found...
		validatedOAuth1Parameters(oauthParameters);
		
		String consumerKey = oauthParameters.get(OAuth1Constants.OAUTH_CONSUMER_KEY)[0];
		OAuthSignatureMethod signatureMethod = OAuthSignatureMethod.of(oauthParameters.get(OAuth1Constants.OAUTH_SIGNATURE_METHOD)[0]);
		Long timestamp = oauthParameters.containsKey(OAuth1Constants.OAUTH_TIMESTAMP) ? Long.parseLong(oauthParameters.get(OAuth1Constants.OAUTH_TIMESTAMP)[0]) : null;
		String nonce = oauthParameters.get(OAuth1Constants.OAUTH_NONCE)[0];
		String signature = oauthParameters.get(OAuth1Constants.OAUTH_SIGNATURE)[0];
		String version = oauthParameters.containsKey(OAuth1Constants.OAUTH_VERSION) ? oauthParameters.get(OAuth1Constants.OAUTH_VERSION)[0] : null;
		if (oauthParameters.containsKey(OAuth1Constants.OAUTH_CALLBACK)) {
			String callback = oauthParameters.get(OAuth1Constants.OAUTH_CALLBACK)[0];
			return new OAuth1TemporaryCredentials(consumerKey, signatureMethod, timestamp, nonce, signature, version, callback);
		}
		
		if (oauthParameters.containsKey(OAuth1Constants.OAUTH_TOKEN) && oauthParameters.containsKey(OAuth1Constants.OAUTH_VERIFIER)) {
			String token = oauthParameters.get(OAuth1Constants.OAUTH_TOKEN)[0];
			String verifier = oauthParameters.get(OAuth1Constants.OAUTH_VERIFIER)[0];
			return new OAuth1TokenCredentials(consumerKey, signatureMethod, timestamp, nonce, signature, version, token, verifier);
		}
		
		if (oauthParameters.containsKey(OAuth1Constants.OAUTH_TOKEN)) {
			String token = oauthParameters.get(OAuth1Constants.OAUTH_TOKEN)[0];
			return new OAuth1AuthenticatedCredentials(consumerKey, signatureMethod, timestamp, nonce, signature, version, token);
		}
		
		throw new MalformedChallengeException("Unable to create an OAuth credentials with the following credential string \"" + authorizationString + "\".");
	}
	
	private void validatedOAuth1Parameters(Map<String, String[]> oauthParameters) throws MalformedChallengeException {
		for (Entry<String, String[]> entry : oauthParameters.entrySet()) {
			String name = entry.getKey();
			if (!HttpConstants.REALM.equals(name) && !name.startsWith("oauth_")) {
				throw new MalformedChallengeException("Invalid OAuth parameter '" + name + "'.");
			}
			
			String[] values = entry.getValue();
			if (values.length != 1) {
				throw new MalformedChallengeException("OAuth parameter '" + name + "' contains multiple (" + values.length + ") values. Expected only 1 value.");
			}
			
			if (OAuth1Constants.OAUTH_VERSION.equals(name) && !OAuth1Constants.VERSION_1_0.equals(values[0])) {
				throw new MalformedChallengeException("OAuth parameter '" + OAuth1Constants.OAUTH_VERSION + "' contains value that does not equal to value \"" + OAuth1Constants.VERSION_1_0 + "\". See paragraph 3.2 of RFC 5849.");
			}
		}
	}
	
	public static class OAuthCredentialsAuthenticationSchemeBuilder extends CredentialsAuthenticationSchemeBuilder<OAuth1Credentials, OAuthCredentialsAuthenticationScheme> implements AuthenticationSchemeBuilder<OAuthCredentialsAuthenticationScheme> {

		/**
		 * @param requestHeaderName
		 */
		public OAuthCredentialsAuthenticationSchemeBuilder(String requestHeaderName) {
			super(requestHeaderName);
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see za.co.sindi.oauth.server.http.AuthenticationSchemeBuilder#build()
		 */
		@Override
		public OAuthCredentialsAuthenticationScheme build() {
			// TODO Auto-generated method stub
			return new OAuthCredentialsAuthenticationScheme(this);
		}
	}
}
