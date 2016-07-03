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
import za.co.sindi.oauth.client.endpoints.LinkedInEndpoints;
import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.nonce.DefaultNonceGenerator;
import za.co.sindi.oauth.client.request.oauth1.OAuth1Request;
import za.co.sindi.oauth.client.request.oauth1.enums.OAuthSignature;
import za.co.sindi.oauth.client.transport.factory.HttpTransportFactory;
import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * 
 */

/**
 * @author Buhake Sindi
 * @since 17 February 2012
 *
 */
public class LinkedInOAuthTest {

	private static final String API_KEY = "I9DvH3zT4c-sjmrQTmo_AeJOfi8v8n1ChYHYAV8A3siVLyu1qLZqPq_HiGecD0bp";
	private static final String API_SECRET  = "YhSX7rfm5fBkfJl4QTfbgNGMkLgDjljvXx0dwKL4GcYXpNXn0SHpiRKcolO_XEHz";
	private static final String OAUTH_CALLBACK = OAuth1Constants.OOB;
	
	@Test
	public void testTwitterOAuth() {
		try {
			OAuth1Endpoints endpoints = new LinkedInEndpoints();
			OAuth1ClientCredentials credentials = new OAuth1ClientCredentials(API_KEY, API_SECRET);
			OAuth1Client client = new OAuth1Client(endpoints);
			OAuth1Request requestTokenRequest = client.getRequestTokenRequest()
					.setRealm("https://api.linkedin.com/")
					.setClientCredentials(credentials)
					.setSignature(OAuthSignature.HMAC_SHA1)
					.setNonceGenerator(new DefaultNonceGenerator())
					.setCallback(OAUTH_CALLBACK)
					.setIncludeOAuthVersion(true);
			requestTokenRequest.setTransportFactory(new HttpTransportFactory());
			RefreshToken requestToken = requestTokenRequest.execute();
			Assert.assertNotNull(requestToken);
			System.out.println("Token:" + requestToken.getToken() + "\r\nToken Secret: " + requestToken.getTokenSecret());
			
			AuthorizationUrl authorizationUrlRequest = client.getAuthorizationUrl().setToken(requestToken.getToken());
			BrowserManager.getInstance().getBrowser().browse(authorizationUrlRequest.generateUrl());
		} catch (OAuthRequestException e) {
			// TODO Auto-generated catch block
			Assert.fail(e.getLocalizedMessage());
		} catch (OAuthResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	public static void main(String[] args) {
		new LinkedInOAuthTest().testTwitterOAuth();
	}
}
