/**
 * 
 */
package za.co.sindi.oauth.client.endpoints;

import za.co.sindi.oauth.client.OAuth1Endpoints;

/**
 * @author Buhake Sindi
 * @since 10 February 2012
 *
 */
public class TwitterEndpoints extends OAuth1Endpoints {

	private static final String TWITTER_OAUTH_ENDPOINT = "https://api.twitter.com/oauth/";
	
	/**
	 * 
	 */
	public TwitterEndpoints() {
		super(TWITTER_OAUTH_ENDPOINT + "request_token", TWITTER_OAUTH_ENDPOINT + "authorize", TWITTER_OAUTH_ENDPOINT + "access_token");
		// TODO Auto-generated constructor stub
	}
}
