/**
 * 
 */
package za.co.sindi.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import za.co.sindi.oauth.client.OAuth2Client;
import za.co.sindi.oauth.client.OAuth2Endpoints;
import za.co.sindi.oauth.client.ResponseHandler;
import za.co.sindi.oauth.client.credentials.oauth2.AccessToken;
import za.co.sindi.oauth.client.credentials.oauth2.OAuth2ClientCredentials;
import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.request.oauth2.AuthorizationGrant;
import za.co.sindi.oauth.client.request.oauth2.OAuth2AccesTokenRequest;
import za.co.sindi.oauth.client.request.oauth2.OAuth2ProtectedResourceRequest;
import za.co.sindi.oauth.client.transport.Response;
import za.co.sindi.oauth.client.transport.factory.HttpTransportFactory;
import za.co.sindi.oauth.client.transport.http.auth.OAuth2BearerAuthentication;
import za.co.sindi.oauth.util.IOUtils;

/**
 * @author Bienfait Sindi
 * @since 30 May 2016
 *
 */
public class KubenOAuthTest {

	private static final String API_KEY = "foo";
	private static final String API_SECRET = "foosecret";
	
	@Test
	public void testKuben() {
		try {
			OAuth2Endpoints endpoints = new OAuth2Endpoints("http://10.200.195.39:9000/oauth/token", "http://10.200.195.39:9000/oauth/token");
			OAuth2Client client = new OAuth2Client(endpoints);
//			AuthorizationUrl authorizationUrlRequest = client.getAuthorizationUrl()
//																	.setClientId(API_KEY);
//			BrowserManager browserManager = BrowserManager.getInstance();
//			Browser browser = browserManager.getBrowser();
//			browser.browse(authorizationUrlRequest.generateUrl());
			
			OAuth2ClientCredentials clientCredentials = new OAuth2ClientCredentials(API_KEY, API_SECRET);
			OAuth2AccesTokenRequest accessTokenRequest = client.getAccessTokenRequest();
			accessTokenRequest.setAuthorizationGrant(AuthorizationGrant.password("admin", "admin")).setClientCredentials(clientCredentials);
			accessTokenRequest.setTransportFactory(new HttpTransportFactory());
			AccessToken accessToken = accessTokenRequest.execute();
			Assert.assertNotNull(accessToken);
			System.out.println(accessToken.getAccessToken());
			
			//Get Protected resource...
			OAuth2ProtectedResourceRequest protectedResourceRequest = client.getProtectedResourceRequest("GET", "http://10.200.195.39:8080/api/products");
			protectedResourceRequest.setAuthorization(new OAuth2BearerAuthentication(accessToken));
			protectedResourceRequest.setTransportFactory(new HttpTransportFactory());
			String resourceMessage = protectedResourceRequest.execute(new ResponseHandler<String>() {

				/* (non-Javadoc)
				 * @see za.co.sindi.oauth.client.ResponseHandler#handleResponse(za.co.sindi.oauth.client.transport.Response)
				 */
				@Override
				public String handleResponse(Response response) throws OAuthResponseException {
					// TODO Auto-generated method stub
					try {
						return IOUtils.toString(response.getResponseStream(), "UTF-8");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						throw new OAuthResponseException(e);
					}
				}
			});
			System.out.println("Message: " + resourceMessage);
		} catch (OAuthRequestException e) {
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
		new KubenOAuthTest().testKuben();
	}
}
