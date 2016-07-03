/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.basic;

import za.co.sindi.codec.Base64Codec;
import za.co.sindi.codec.exception.DecodingException;
import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.exception.HttpException;
import za.co.sindi.oauth.server.exception.http.MalformedChallengeException;
import za.co.sindi.oauth.server.http.auth.AbstractCredentialsAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.AuthSchemes;
import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.UsernamePasswordCredentials;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public class BasicCredentialsAuthenticationScheme extends AbstractCredentialsAuthenticationScheme<UsernamePasswordCredentials> implements CredentialsAuthenticationScheme<UsernamePasswordCredentials> {

    private static final String COLON = ":";
    
    private String charsetName;
    
	/**
	 * @param builder
	 */
	private BasicCredentialsAuthenticationScheme(BasicCredentialsAuthenticationSchemeBuilder builder) {
		super(AuthSchemes.BASIC, builder.getRequestHeaderName());
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.AbstractCredentialsAuthenticationScheme#extractCredentials(za.co.sindi.oauth.server.context.http.HttpRequestContext, java.lang.String)
	 */
	@Override
	protected UsernamePasswordCredentials extractCredentials(HttpRequestContext request, String authorizationString) throws HttpException {
		// TODO Auto-generated method stub
		try {
//			String authorization = authorizationString;
//			if (authorization.startsWith(BASIC_PREFIX)) {
//				authorization = authorization.substring(PREFIX_LENGTH);
//			}
			
//			String authorization = Base64Codec.decode(authorizationString, charsetName);
			String authorization = Base64Codec.getBase64Codec().decode(authorizationString, charsetName);
			
			int colonPos = authorization.indexOf(COLON);
			String username = authorization.substring(0);
			String password = null;
			if (colonPos > -1) {
				username = authorization.substring(0, colonPos);
				password = authorization.substring(colonPos + 1);
			}
			
			return new UsernamePasswordCredentials(username, password);
		} catch (DecodingException e) {
			// TODO Auto-generated catch block
			throw new MalformedChallengeException("Authorization string '" + authorizationString + "' cannot be decoded using Base 64 decoding algorithm.", e);
		}
	}
	
	public static class BasicCredentialsAuthenticationSchemeBuilder extends CredentialsAuthenticationSchemeBuilder<UsernamePasswordCredentials, BasicCredentialsAuthenticationScheme> implements AuthenticationSchemeBuilder<BasicCredentialsAuthenticationScheme> {

		/**
		 * @param requestHeaderName
		 */
		public BasicCredentialsAuthenticationSchemeBuilder(String requestHeaderName) {
			super(requestHeaderName);
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see za.co.sindi.oauth.server.http.AuthenticationSchemeBuilder#build()
		 */
		@Override
		public BasicCredentialsAuthenticationScheme build() {
			// TODO Auto-generated method stub
			return new BasicCredentialsAuthenticationScheme(this);
		}
	}
}
