/**
 * 
 */
package za.co.sindi.oauth.client;

import za.co.sindi.oauth.util.OAuth2Util;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public class OAuth2Endpoints extends OAuthEndpoints {

	/**
	 * @param authorizationUrl
	 * @param accessTokenUrl
	 */
	public OAuth2Endpoints(String authorizationUrl, String accessTokenUrl) {
		super(OAuth2Util.normalizeUrl(authorizationUrl), OAuth2Util.normalizeUrl(accessTokenUrl));
		// TODO Auto-generated constructor stub
	}
}
