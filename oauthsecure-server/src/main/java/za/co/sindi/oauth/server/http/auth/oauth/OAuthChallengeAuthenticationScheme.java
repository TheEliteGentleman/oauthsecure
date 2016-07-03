/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.oauth;

import za.co.sindi.oauth.server.http.auth.AuthSchemes;
import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.ChallengeAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.ChallengeAuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.RFC2617ChallengeAuthenticationScheme;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public class OAuthChallengeAuthenticationScheme extends RFC2617ChallengeAuthenticationScheme implements ChallengeAuthenticationScheme {

	private int responseStatusCode;
	private String challenge;
	
	/**
	 * @param builder
	 */
	private OAuthChallengeAuthenticationScheme(OAuthChallengeAuthenticationSchemeBuilder builder) {
		super(AuthSchemes.BASIC, builder.realm, builder.getResponseHeaderName());
		this.responseStatusCode = builder.responseStatusCode;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.AbstractChallengeAuthenticationScheme#getResponseStatusCode()
	 */
	@Override
	protected int getResponseStatusCode() {
		// TODO Auto-generated method stub
		return responseStatusCode;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.AbstractAuthenticationSchemeChallenge#generateChallenge()
	 */
	@Override
	protected String generateChallenge() {
		// TODO Auto-generated method stub
		if (challenge == null || challenge.isEmpty()) {
			String realm = getRealm();
			if (realm == null || realm.isEmpty()) {
				throw new IllegalStateException("No realm was specified as specified in RFC 5849 Chapter 3.5.1.");
			}
			
			challenge = "realm=\"" + realm + "\"";
		}
		
		return challenge;
	}
	
	public static class OAuthChallengeAuthenticationSchemeBuilder extends ChallengeAuthenticationSchemeBuilder<OAuthChallengeAuthenticationScheme> implements AuthenticationSchemeBuilder<OAuthChallengeAuthenticationScheme> {

		private int responseStatusCode;
		private String realm;
		
		/**
		 * @param responseStatusCode
		 * @param responseHeaderName
		 */
		public OAuthChallengeAuthenticationSchemeBuilder(int responseStatusCode, String responseHeaderName) {
			super(responseHeaderName);
			// TODO Auto-generated constructor stub
			this.responseStatusCode = responseStatusCode;
		}

		/**
		 * @param realm the realm to set
		 */
		public OAuthChallengeAuthenticationSchemeBuilder setRealm(String realm) {
			this.realm = realm;
			return this;
		}

		/* (non-Javadoc)
		 * @see za.co.sindi.oauth.server.http.AuthenticationSchemeBuilder#build()
		 */
		@Override
		public OAuthChallengeAuthenticationScheme build() {
			// TODO Auto-generated method stub
			return new OAuthChallengeAuthenticationScheme(this);
		}
	}
}
