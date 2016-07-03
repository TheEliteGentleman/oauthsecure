/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.bearer;

import java.util.regex.Pattern;

import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.exception.HttpException;
import za.co.sindi.oauth.server.exception.http.MalformedChallengeException;
import za.co.sindi.oauth.server.http.auth.AbstractCredentialsAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.AuthSchemes;
import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.TokenCredential;
import za.co.sindi.oauth.server.http.auth.TokenPrincipal;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public class BearerCredentialsAuthenticationScheme extends AbstractCredentialsAuthenticationScheme<TokenCredential> implements CredentialsAuthenticationScheme<TokenCredential> {

	private static final Pattern BEARER_PATTERN = Pattern.compile("[0-9a-zA-Z-._~+/]+");
    
	/**
	 * @param builder
	 */
	private BearerCredentialsAuthenticationScheme(BearerCredentialsAuthenticationSchemeBuilder builder) {
		super(AuthSchemes.BEARER, builder.getRequestHeaderName());
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.AbstractCredentialsAuthenticationScheme#extractCredentials(za.co.sindi.oauth.server.context.http.HttpRequestContext, java.lang.String)
	 */
	@Override
	protected TokenCredential extractCredentials(HttpRequestContext request, String authorizationString) throws HttpException {
		// TODO Auto-generated method stub
		if (!BEARER_PATTERN.matcher(authorizationString).matches()) {
			throw new MalformedChallengeException(getSchemeName() + " contains an invalid access token '" + authorizationString + "'.");
		}
		
		return new TokenCredential(new TokenPrincipal(authorizationString));
	}
	
	public static class BearerCredentialsAuthenticationSchemeBuilder extends CredentialsAuthenticationSchemeBuilder<TokenCredential, BearerCredentialsAuthenticationScheme> implements AuthenticationSchemeBuilder<BearerCredentialsAuthenticationScheme> {

		/**
		 * @param requestHeaderName
		 */
		public BearerCredentialsAuthenticationSchemeBuilder(String requestHeaderName) {
			super(requestHeaderName);
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see za.co.sindi.oauth.server.http.AuthenticationSchemeBuilder#build()
		 */
		@Override
		public BearerCredentialsAuthenticationScheme build() {
			// TODO Auto-generated method stub
			return new BearerCredentialsAuthenticationScheme(this);
		}
	}
}
