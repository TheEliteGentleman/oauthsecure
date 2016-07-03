/**
 * 
 */
package za.co.sindi.oauth.client.endpoints;

import za.co.sindi.oauth.client.OAuth2Endpoints;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public class FacebookEndpoints extends OAuth2Endpoints {

	private static final String FACEBOOK_OAUTH_ENPOINT = "https://graph.facebook.com/oauth/";
	
	/**
	 * 
	 */
	public FacebookEndpoints() {
		super(FACEBOOK_OAUTH_ENPOINT + "authorize", FACEBOOK_OAUTH_ENPOINT + "access_token");
		// TODO Auto-generated constructor stub
	}
}
