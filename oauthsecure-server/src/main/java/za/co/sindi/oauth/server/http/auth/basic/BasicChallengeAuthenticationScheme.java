/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.basic;

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
public class BasicChallengeAuthenticationScheme extends RFC2617ChallengeAuthenticationScheme implements ChallengeAuthenticationScheme {

	private int responseStatusCode;
	private String challenge;
	
	/**
	 * @param builder
	 */
	private BasicChallengeAuthenticationScheme(BasicChallengeAuthenticationSchemeBuilder builder) {
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
				throw new IllegalStateException("No realm was specified as specified in RFC 2617.");
			}
			
			challenge = "realm=\"" + realm + "\"";
		}
		
		return challenge;
	}
	
	public static class BasicChallengeAuthenticationSchemeBuilder extends ChallengeAuthenticationSchemeBuilder<BasicChallengeAuthenticationScheme> implements AuthenticationSchemeBuilder<BasicChallengeAuthenticationScheme> {

		private int responseStatusCode;
		private String realm;
		
		/**
		 * @param responseStatusCode
		 * @param responseHeaderName
		 */
		public BasicChallengeAuthenticationSchemeBuilder(int responseStatusCode, String responseHeaderName) {
			super(responseHeaderName);
			// TODO Auto-generated constructor stub
			this.responseStatusCode = responseStatusCode;
		}

		/**
		 * @param realm the realm to set
		 */
		public BasicChallengeAuthenticationSchemeBuilder setRealm(String realm) {
			this.realm = realm;
			return this;
		}

		/* (non-Javadoc)
		 * @see za.co.sindi.oauth.server.http.AuthenticationSchemeBuilder#build()
		 */
		@Override
		public BasicChallengeAuthenticationScheme build() {
			// TODO Auto-generated method stub
			return new BasicChallengeAuthenticationScheme(this);
		}
	}
}
