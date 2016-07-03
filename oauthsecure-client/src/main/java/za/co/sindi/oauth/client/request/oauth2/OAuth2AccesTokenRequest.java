/**
 * 
 */
package za.co.sindi.oauth.client.request.oauth2;

import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;

import org.apache.log4j.Level;

import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.client.ResponseHandler;
import za.co.sindi.oauth.client.credentials.oauth2.AccessToken;
import za.co.sindi.oauth.client.credentials.oauth2.OAuth2ClientCredentials;
import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.parameters.oauth2.OAuth2Parameters;
import za.co.sindi.oauth.client.transport.HttpAuthorization;
import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.http.EntityBasedHttpRequest;
import za.co.sindi.oauth.client.transport.http.HttpParameters;
import za.co.sindi.oauth.client.transport.http.HttpRequest;
import za.co.sindi.oauth.client.transport.http.UriQueryParameterHttpRequest;
import za.co.sindi.oauth.client.transport.http.auth.HttpBasicAuthorization;
import za.co.sindi.oauth.client.transport.http.entity.HttpParameterEntity;
import za.co.sindi.oauth.core.utils.OAuth2Constants;

/**
 * @author Buhake Sindi
 * @since 13 March 2012
 *
 */
public class OAuth2AccesTokenRequest extends OAuth2Request {

	private AuthorizationGrant authorizationGrant;
	private OAuth2ClientCredentials clientCredentials;
	
	/**
	 * @param requestMethod
	 * @param requestUrl
	 */
	public OAuth2AccesTokenRequest(String requestMethod, String requestUrl) {
		super(requestMethod, requestUrl);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param requestMethod
	 * @param requestUrl
	 * @param responseHandler
	 */
	public OAuth2AccesTokenRequest(String requestMethod, String requestUrl, ResponseHandler<AccessToken> responseHandler) {
		super(requestMethod, requestUrl, responseHandler);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.request.oauth2.OAuth2Request#authenticateHttpRequest(net.oauth.transport.http.HttpRequest)
	 */
	@Override
	protected void authenticateHttpRequest(HttpRequest request) throws OAuthRequestException, AuthorizationException {
		// TODO Auto-generated method stub
//		if (clientCredentials == null) {
//			throw new OAuthRequestException("OAuth 2 client credential is required.");
//		}
		
		if (authorizationGrant == null) {
			throw new OAuthRequestException("No authorization " + OAuth2Constants.GRANT_TYPE + " provided. Cannot execute request.");
		}
		
		try {
			OAuth2Parameters oauth2Params = authorizationGrant.getParameters();
			String grantType = oauth2Params.getGrantType();
			if (clientCredentials == null) {
				//Validate if the client credentials was passed by using the AuthorizationGrant
				String clientId = oauth2Params.getClientId();
				String clientSecret = oauth2Params.getClientSecret();
				if (clientId == null || clientId.isEmpty()) {
					throw new OAuthRequestException(OAuth2Constants.CLIENT_ID + " was not found as request parameter. This occurred because there was no oauth 2 credentials specified to do generate HTTP Authorization header.");
				}
				
				if (!OAuth2Constants.PASSWORD.equals(grantType) && clientSecret == null || clientSecret.isEmpty()) {
					throw new OAuthRequestException(OAuth2Constants.CLIENT_SECRET + " was not found as request parameter. This occurred because there was no oauth 2 credentials specified to do generate HTTP Authorization header.");
				}
			}
			
			HttpParameters httpParams = new HttpParameters();
			
			//Add Grant-Type & oauth 2 parameters to HTTP parameters
			for (Entry<String, String> entry : oauth2Params.toMap().entrySet()) {
				if (OAuth2Constants.PASSWORD.equals(grantType) && OAuth2Constants.CLIENT_SECRET.equals(entry.getKey())) {
					if (logger.isEnabledFor(Level.WARN)) {
						logger.warn("OAuth 2 Grant Type '" + OAuth2Constants.PASSWORD + "' contains " + OAuth2Constants.CLIENT_SECRET + " parameter. Completely ignoring this parameter to conform to OAuth 2 Specification.");
					}
					continue;
				}
				httpParams.setParameter(entry.getKey(), entry.getValue());
			}
			
			if (httpParams != null && !httpParams.isEmpty()) {
				if (request instanceof UriQueryParameterHttpRequest) {
					((UriQueryParameterHttpRequest)request).setHttpParameters(httpParams);
				} else if (request instanceof EntityBasedHttpRequest) {
					((EntityBasedHttpRequest)request).setEntity(new HttpParameterEntity(httpParams, "UTF-8"));
				}
			}
			
			if (clientCredentials != null) {
				//Authenticate
				HttpAuthorization authorization = new HttpBasicAuthorization(clientCredentials.getClientId(), clientCredentials.getSecret());
				authorization.authenticate(request);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthRequestException(e);
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthRequestException(e);
		}
	}
	
	/**
	 * @param authorizationGrant the authorizationGrant to set
	 */
	public OAuth2AccesTokenRequest setAuthorizationGrant(AuthorizationGrant authorizationGrant) {
		this.authorizationGrant = authorizationGrant;
		return this;
	}

	/**
	 * @param clientCredentials the clientCredentials to set
	 */
	public OAuth2AccesTokenRequest setClientCredentials(OAuth2ClientCredentials clientCredentials) {
		this.clientCredentials = clientCredentials;
		return this;
	}
}
