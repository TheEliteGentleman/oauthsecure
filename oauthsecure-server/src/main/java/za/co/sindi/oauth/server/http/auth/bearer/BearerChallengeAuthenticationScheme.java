/**
 * 
 */
package za.co.sindi.oauth.server.http.auth.bearer;

import za.co.sindi.oauth.server.http.auth.AbstractChallengeAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.AuthSchemes;
import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilder;
import za.co.sindi.oauth.server.http.auth.ChallengeAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.ChallengeAuthenticationSchemeBuilder;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public class BearerChallengeAuthenticationScheme extends AbstractChallengeAuthenticationScheme implements ChallengeAuthenticationScheme {

	private BearerError error;
	private String errorDescription;
	private String realm;
	private String challenge;
	
	/**
	 * @param schemeName
	 * @param responseStatusCode
	 * @param responseHeaderName
	 * @param builder
	 */
	private BearerChallengeAuthenticationScheme(BearerChallengeAuthenticationSchemeBuilder builder) {
		super(AuthSchemes.BEARER, builder.getResponseHeaderName());
		if (error == null) {
			throw new IllegalArgumentException("No Bearer error was specified.");
		}
		this.error = builder.error;
		this.errorDescription = builder.errorDescription;
		this.realm = builder.realm;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.AbstractChallengeAuthenticationScheme#getResponseStatusCode()
	 */
	@Override
	protected int getResponseStatusCode() {
		// TODO Auto-generated method stub
		return error.getStatusCode();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.AbstractAuthenticationSchemeChallenge#generateChallenge()
	 */
	@Override
	protected String generateChallenge() {
		// TODO Auto-generated method stub
		if (challenge == null || challenge.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			if (realm != null && !realm.isEmpty()) {
				sb.append("realm=").append("\"").append(realm).append("\"");
			}
			
			if (error != null) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				
				sb.append("error=").append("\"").append(error.toString()).append("\"");
			}
			
			if (errorDescription != null && !errorDescription.isEmpty()) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				
				sb.append("error_description=").append("\"").append(errorDescription).append("\"");
			}
			
			challenge = sb.toString();
		}
		
		return challenge;
	}
	
	public static class BearerChallengeAuthenticationSchemeBuilder extends ChallengeAuthenticationSchemeBuilder<BearerChallengeAuthenticationScheme> implements AuthenticationSchemeBuilder<BearerChallengeAuthenticationScheme> {

		private BearerError error;
		private String errorDescription;
		private String realm;
		
		/**
		 * @param responseStatusCode
		 * @param responseHeaderName
		 */
		public BearerChallengeAuthenticationSchemeBuilder(String responseHeaderName) {
			super(responseHeaderName);
			// TODO Auto-generated constructor stub
		}

		/**
		 * @param error the error to set
		 */
		public BearerChallengeAuthenticationSchemeBuilder setError(BearerError error) {
			this.error = error;
			return this;
		}

		/**
		 * @param errorDescription the errorDescription to set
		 */
		public BearerChallengeAuthenticationSchemeBuilder setErrorDescription(String errorDescription) {
			this.errorDescription = errorDescription;
			return this;
		}

		/**
		 * @param realm the realm to set
		 */
		public BearerChallengeAuthenticationSchemeBuilder setRealm(String realm) {
			this.realm = realm;
			return this;
		}

		/* (non-Javadoc)
		 * @see za.co.sindi.oauth.server.http.AuthenticationSchemeBuilder#build()
		 */
		@Override
		public BearerChallengeAuthenticationScheme build() {
			// TODO Auto-generated method stub
			return new BearerChallengeAuthenticationScheme(this);
		}
	}
}
