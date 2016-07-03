/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * @author Bienfait Sindi
 * @since 08 Feburary 2016
 *
 */
public abstract class ChallengeAuthenticationSchemeBuilder<T extends ChallengeAuthenticationScheme> implements AuthenticationSchemeBuilder<T> {

	private String responseHeaderName;
	
	/**
	 * @param responseHeaderName
	 */
	protected ChallengeAuthenticationSchemeBuilder(String responseHeaderName) {
		super();
		if (responseHeaderName == null || responseHeaderName.isEmpty()) {
			throw new IllegalArgumentException("No response header name was specified.");
		}
		
		this.responseHeaderName = responseHeaderName;
	}

	/**
	 * @return the responseHeaderName
	 */
	public String getResponseHeaderName() {
		return responseHeaderName;
	}
}
