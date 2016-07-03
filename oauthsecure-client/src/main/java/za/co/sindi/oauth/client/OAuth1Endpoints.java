/**
 * 
 */
package za.co.sindi.oauth.client;

/**
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public class OAuth1Endpoints extends OAuthEndpoints {

	private String requestTokenUrl;

	/**
	 * 
	 * @param requestTokenUrl
	 * @param authorizationUrl
	 * @param accessTokenUrl
	 */
	public OAuth1Endpoints(String requestTokenUrl, String authorizationUrl, String accessTokenUrl) {
		super(authorizationUrl, accessTokenUrl);
		this.requestTokenUrl = requestTokenUrl;
	}

	/**
	 * @return the requestTokenUrl
	 */
	public String getRequestTokenUrl() {
		return requestTokenUrl;
	}
}
