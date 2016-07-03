/**
 * 
 */
package za.co.sindi.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import za.co.sindi.oauth.client.AuthorizationUrl;
import za.co.sindi.oauth.client.OAuth2Client;
import za.co.sindi.oauth.client.OAuth2Endpoints;
import za.co.sindi.oauth.client.browser.Browser;
import za.co.sindi.oauth.client.browser.BrowserManager;
import za.co.sindi.oauth.client.credentials.oauth2.AccessToken;
import za.co.sindi.oauth.client.credentials.oauth2.OAuth2ClientCredentials;
import za.co.sindi.oauth.client.endpoints.FacebookEndpoints;
import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.request.oauth2.AuthorizationGrant;
import za.co.sindi.oauth.client.request.oauth2.OAuth2AccesTokenRequest;
import za.co.sindi.oauth.client.transport.factory.HttpTransportFactory;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public class FacebookOAuthTest {

	private static final String API_KEY = "381571044440";
	private static final String API_SECRET = "460f1f0ca32bbf32458a8b8dec6a216e";
	
	@Test
	public void testFacebook() {
		try {
			OAuth2Endpoints endpoints = new FacebookEndpoints();
			OAuth2Client client = new OAuth2Client(endpoints);
			AuthorizationUrl authorizationUrlRequest = client.getAuthorizationUrl()
																	.setClientId(API_KEY);
			BrowserManager browserManager = BrowserManager.getInstance();
			Browser browser = browserManager.getBrowser();
			browser.browse(authorizationUrlRequest.generateUrl());
			
			OAuth2ClientCredentials clientCredentials = new OAuth2ClientCredentials(API_KEY, API_SECRET);
			OAuth2AccesTokenRequest accessTokenRequest = client.getAccessTokenRequest();
			accessTokenRequest.setAuthorizationGrant(AuthorizationGrant.password("buhake@hotmail.com", "phox0t1c")).setClientCredentials(clientCredentials);
			accessTokenRequest.setTransportFactory(new HttpTransportFactory());
			AccessToken accessToken = accessTokenRequest.execute();
			Assert.assertNotNull(accessToken);
			System.out.println(accessToken.getAccessToken());
		} catch (OAuthRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		} catch (OAuthResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}	
	}
	
	public static void main(String[] args) {
		new FacebookOAuthTest().testFacebook();
	}
}
