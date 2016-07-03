/**
 * 
 */
package za.co.sindi.oauth.client.request.oauth1;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;

import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.client.NonceGenerator;
import za.co.sindi.oauth.client.ResponseHandler;
import za.co.sindi.oauth.client.credentials.oauth1.OAuth1ClientCredentials;
import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.request.AbstractOAuthRequest;
import za.co.sindi.oauth.client.request.oauth1.enums.OAuthSignature;
import za.co.sindi.oauth.client.request.oauth1.enums.RequestType;
import za.co.sindi.oauth.client.transport.Request;
import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.factory.HttpTransportFactory;
import za.co.sindi.oauth.client.transport.http.EntityBasedHttpRequest;
import za.co.sindi.oauth.client.transport.http.HttpParameters;
import za.co.sindi.oauth.client.transport.http.HttpRequest;
import za.co.sindi.oauth.client.transport.http.UriQueryParameterHttpRequest;
import za.co.sindi.oauth.client.transport.http.auth.OAuth1Authorization.OAuth1AuthorizationBuilder;
import za.co.sindi.oauth.client.transport.http.entity.HttpParameterEntity;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public class OAuth1Request extends AbstractOAuthRequest {
	
	//Needed oauth parameters
	private OAuth1AuthorizationBuilder oauthAuthBuilder;
	private HttpParameters additionalParameters;
	
	/**
	 * 
	 * @param requestType
	 * @param requestMethod
	 * @param requestUrl
	 */
	public OAuth1Request(RequestType requestType, String requestMethod, String requestUrl) {
		this(requestType, requestMethod, requestUrl, null);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param requestType
	 * @param requestMethod
	 * @param requestUrl
	 * @param responseHandler
	 */
	public OAuth1Request(RequestType requestType, String requestMethod, String requestUrl, ResponseHandler<?> responseHandler) {
		super(requestMethod, requestUrl, responseHandler);
		// TODO Auto-generated constructor stub
		oauthAuthBuilder = new OAuth1AuthorizationBuilder(requestType);
		//Sets transport factory to HttpTransportFactory
		setTransportFactory(new HttpTransportFactory());
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.request.AbstractOAuthRequest#setTransportFactory(net.oauth.transport.factory.TransportFactory)
	 */
	public void setTransportFactory(HttpTransportFactory transportFactory) {
		// TODO Auto-generated method stub
		super.setTransportFactory(transportFactory);
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.request.AbstractOAuthRequest#getTransportFactory()
	 */
	@Override
	public HttpTransportFactory getTransportFactory() {
		// TODO Auto-generated method stub
		return (HttpTransportFactory) super.getTransportFactory();
	}

	/**
	 * @param clientCredentials the clientCredentials to set
	 */
	public OAuth1Request setClientCredentials(OAuth1ClientCredentials clientCredentials) {
		oauthAuthBuilder.setClientCredentials(clientCredentials);
		return this;
	}

	/**
	 * @param realm the realm to set
	 */
	public OAuth1Request setRealm(String realm) {
		oauthAuthBuilder.setRealm(realm);
		return this;
	}

	/**
	 * @param token the token to set
	 */
	public OAuth1Request setToken(String token) {
		oauthAuthBuilder.setToken(token);
		return this;
	}

	/**
	 * @param tokenSecret the tokenSecret to set
	 */
	public OAuth1Request setTokenSecret(String tokenSecret) {
		oauthAuthBuilder.setTokenSecret(tokenSecret);
		return this;
	}

	/**
	 * @param verifier the verifier to set
	 */
	public OAuth1Request setVerifier(String verifier) {
		oauthAuthBuilder.setVerifier(verifier);
		return this;
	}

	/**
	 * @param callback the callback to set
	 */
	public OAuth1Request setCallback(String callback) {
		oauthAuthBuilder.setCallback(callback);
		return this;
	}

	/**
	 * @param signature the signature to set
	 */
	public OAuth1Request setSignature(OAuthSignature signature) {
		oauthAuthBuilder.setSignature(signature);
		return this;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public OAuth1Request setPrivateKey(PrivateKey privateKey) {
		oauthAuthBuilder.setPrivateKey(privateKey);
		return this;
	}

	/**
	 * @param nonceGenerator the nonceGenerator to set
	 */
	public OAuth1Request setNonceGenerator(NonceGenerator nonceGenerator) {
		oauthAuthBuilder.setNonceGenerator(nonceGenerator);
		return this;
	}

	/**
	 * @param additionalParameters the additionalParameters to set
	 */
	public OAuth1Request setAdditionalParameters(HttpParameters additionalParameters) {
		this.additionalParameters = additionalParameters;
		return this;
	}

	/**
	 * @param includeOAuthVersion the includeOAuthVersion to set
	 */
	public OAuth1Request setIncludeOAuthVersion(boolean includeOAuthVersion) {
		oauthAuthBuilder.setIncludeOAuthVersion(includeOAuthVersion);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see net.oauth.client.request.AbstractOAuthRequest#authenticateRequest(net.oauth.transport.Request)
	 */
	@Override
	protected void authenticateRequest(Request request) throws OAuthRequestException, AuthorizationException {
		// TODO Auto-generated method stub
		if (!(request instanceof HttpRequest)) {
			throw new IllegalArgumentException("Request is not instance of " + HttpRequest.class.getName());
		}
		
		if (additionalParameters != null && !additionalParameters.isEmpty()) {
			oauthAuthBuilder.setAdditionalParameters(additionalParameters.toMap());
		}
		
		try {
			if (additionalParameters != null && !additionalParameters.isEmpty()) {
				if (request instanceof UriQueryParameterHttpRequest) {
					((UriQueryParameterHttpRequest)request).setHttpParameters(additionalParameters);
				} else if (request instanceof EntityBasedHttpRequest) {
					((EntityBasedHttpRequest)request).setEntity(new HttpParameterEntity(additionalParameters, "UTF-8"));
				}
			}
			
			//Authenticate
			oauthAuthBuilder.build().authenticate((HttpRequest) request);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthRequestException(e);
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthRequestException(e);
		}
	}
}
