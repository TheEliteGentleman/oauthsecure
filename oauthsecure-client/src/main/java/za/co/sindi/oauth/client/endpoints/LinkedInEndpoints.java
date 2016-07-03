/**
 * 
 */
package za.co.sindi.oauth.client.endpoints;

import za.co.sindi.oauth.client.OAuth1Endpoints;

/**
 * @author Buhake Sindi
 * @since 17 February 2012
 *
 */
public class LinkedInEndpoints extends OAuth1Endpoints {

	private static final String LINKEDIN_OAUTH_ENDPOINT = "https://api.linkedin.com/uas/oauth/";
	
	/**
	 * 
	 */
	public LinkedInEndpoints() {
		super(LINKEDIN_OAUTH_ENDPOINT + "requestToken", LINKEDIN_OAUTH_ENDPOINT + "authorize", LINKEDIN_OAUTH_ENDPOINT + "accessToken");
		// TODO Auto-generated constructor stub
	}
}
