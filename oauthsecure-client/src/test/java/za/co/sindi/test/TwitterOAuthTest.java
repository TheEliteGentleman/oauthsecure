package za.co.sindi.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import za.co.sindi.oauth.client.AuthorizationUrl;
import za.co.sindi.oauth.client.OAuth1Client;
import za.co.sindi.oauth.client.OAuth1Endpoints;
import za.co.sindi.oauth.client.browser.BrowserManager;
import za.co.sindi.oauth.client.credentials.oauth1.OAuth1ClientCredentials;
import za.co.sindi.oauth.client.credentials.oauth1.RefreshToken;
import za.co.sindi.oauth.client.endpoints.TwitterEndpoints;
import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.nonce.SecureRandomNonceGenerator;
import za.co.sindi.oauth.client.request.oauth1.OAuth1Request;
import za.co.sindi.oauth.client.request.oauth1.enums.OAuthSignature;
import za.co.sindi.oauth.client.transport.factory.HttpTransportFactory;

/**
 * 
 */

/**
 * @author Buhake Sindi
 * @since 10 February 2012
 *
 */
public class TwitterOAuthTest {

	@Test
	public void testTwitterOAuth() {
		try {
			OAuth1Endpoints endpoints = new TwitterEndpoints();
			OAuth1ClientCredentials credentials = new OAuth1ClientCredentials("e0ddoJdGMLfPj3DSLODA", "3CUTY50IcHYp1c0VV9zTGwv1DsnRL1GdXntsXtyRM");
			OAuth1Client client = new OAuth1Client(endpoints);
			OAuth1Request requestTokenRequest = client.getRequestTokenRequest()
					.setClientCredentials(credentials)
					.setSignature(OAuthSignature.HMAC_SHA1)
					.setNonceGenerator(new SecureRandomNonceGenerator())
					.setCallback("oob");
			requestTokenRequest.setTransportFactory(new HttpTransportFactory());
			RefreshToken requestToken = requestTokenRequest.execute();
			Assert.assertNotNull(requestToken);
			System.out.println(requestToken.getToken() + "-" + requestToken.getTokenSecret());
			
			AuthorizationUrl authorizationUrlRequest = client.getAuthorizationUrl().setToken(requestToken.getToken());
			BrowserManager.getInstance().getBrowser().browse(authorizationUrlRequest.generateUrl());
		} catch (OAuthRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		} catch (OAuthResponseException e) {
			// TODO Auto-generated catch block
			Assert.fail(e.getLocalizedMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	public static void main(String[] args) {
		new TwitterOAuthTest().testTwitterOAuth();
	}
}
