/**
 * 
 */
package za.co.sindi.oauth.client;

/**
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public abstract class OAuthEndpoints {

	private String authorizationUrl;
	private String accessTokenUrl;
	
	/**
	 * @param authorizationUrl
	 * @param accessTokenUrl
	 */
	protected OAuthEndpoints(String authorizationUrl, String accessTokenUrl) {
		super();
		this.authorizationUrl = authorizationUrl;
		this.accessTokenUrl = accessTokenUrl;
	}

	/**
	 * @return the authorizationUrl
	 */
	public String getAuthorizationUrl() {
		return authorizationUrl;
	}
	
	/**
	 * @return the accessTokenUrl
	 */
	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}
}
