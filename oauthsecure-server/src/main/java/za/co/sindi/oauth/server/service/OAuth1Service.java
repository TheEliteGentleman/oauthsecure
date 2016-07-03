/**
 * 
 */
package za.co.sindi.oauth.server.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.codec.StringCodec;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.common.utils.IOUtils;
import za.co.sindi.oauth.core.encoding.PercentEncodingCodec;
import za.co.sindi.oauth.core.encoding.WWWFormURLEncodingCodec;
import za.co.sindi.oauth.core.http.HttpConstants;
import za.co.sindi.oauth.core.http.HttpQueryParameterCodec;
import za.co.sindi.oauth.core.util.HttpParameterCodec;
import za.co.sindi.oauth.core.utils.OAuth1Constants;
import za.co.sindi.oauth.server.context.RequestContext;
import za.co.sindi.oauth.server.context.ResponseContext;
import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.context.http.HttpResponseContext;
import za.co.sindi.oauth.server.exception.HttpException;
import za.co.sindi.oauth.server.exception.ServiceException;
import za.co.sindi.oauth.server.exception.http.MalformedChallengeException;
import za.co.sindi.oauth.server.http.ContentType;
import za.co.sindi.oauth.server.http.RequestMethod;
import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory;
import za.co.sindi.oauth.server.http.auth.CredentialsAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.OAuth1AuthenticatedCredentials;
import za.co.sindi.oauth.server.http.auth.OAuth1Credentials;
import za.co.sindi.oauth.server.http.auth.OAuth1TemporaryCredentials;
import za.co.sindi.oauth.server.http.auth.OAuth1TokenCredentials;
import za.co.sindi.oauth.server.http.auth.OAuthSignatureMethod;
import za.co.sindi.oauth.server.http.auth.oauth.OAuthAuthenticationBuilderFactory;
import za.co.sindi.oauth.server.http.auth.oauth.OAuthChallengeAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.oauth.OAuthCredentialsAuthenticationScheme;
import za.co.sindi.oauth.server.provider.OAuth1DataProvider;
import za.co.sindi.oauth.server.result.Result;

/**
 * @author Bienfait Sindi
 * @since 10 October 2014
 *
 */
public abstract class OAuth1Service implements Service {

	private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private OAuth1DataProvider dataProvider;
	
	/**
	 * @return the dataProvider
	 */
	public OAuth1DataProvider getDataProvider() {
		return dataProvider;
	}

	/**
	 * @param dataProvider the dataProvider to set
	 */
	public void setDataProvider(OAuth1DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.service.OAuthService#service(za.co.sindi.oauth.server.context.RequestContext, za.co.sindi.oauth.server.context.ResponseContext)
	 */
	@Override
	public Result service(RequestContext request, ResponseContext response) throws ServiceException {
		// TODO Auto-generated method stub
		if (!(request instanceof HttpRequestContext) || !(response instanceof HttpResponseContext)) {
			throw new IllegalArgumentException("A HTTP request and response context is required.");
		}
		
		HttpRequestContext httpRequest = (HttpRequestContext) request;
		HttpResponseContext httpResponse = (HttpResponseContext) response;
		return service(httpRequest, httpResponse);
	}
	
	protected Result service(HttpRequestContext request, HttpResponseContext response) throws ServiceException {
		AuthenticationSchemeBuilderFactory<OAuth1Credentials, OAuthCredentialsAuthenticationScheme, OAuthChallengeAuthenticationScheme> authenticationSchemeBuilderFactory = new OAuthAuthenticationBuilderFactory();
		try {
			OAuth1Credentials credentials = retrieveAndValidateCredentials(request, authenticationSchemeBuilderFactory.createCredentialsAuthenticationBuilder(HttpConstants.HEADER_AUTHORIZATION).build());
			
		} catch (HttpException | EncodingException e) {
			// TODO Auto-generated catch block
			if (LOGGER.isLoggable(Level.SEVERE)) {
				LOGGER.log(Level.SEVERE, "Error executing service.", e);
			}
			
			OAuthChallengeAuthenticationScheme authenticationScheme = authenticationSchemeBuilderFactory.createChallengeAuthenticationBuilder(HttpConstants.SC_UNAUTHORIZED, HttpConstants.HEADER_WWW_AUTHENTICATE).build();
			try {
				authenticationScheme.sendAuthenticateResponse(response);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new ServiceException("Unable to send HTTP error response", e1);
			}
		}
	}
	
	private OAuth1Credentials retrieveAndValidateCredentials(HttpRequestContext request, CredentialsAuthenticationScheme<OAuth1Credentials> credentialsAuthenticationScheme) throws HttpException, EncodingException {
		OAuth1Credentials credentials = credentialsAuthenticationScheme.extractCredentials(request);
		validatedOAuth1Signature(request, credentials);
		return credentials;
	}
	
	private void validatedOAuth1Signature(HttpRequestContext request, OAuth1Credentials credentials) throws EncodingException {
		//Validate if signature is correct...
		StringCodec codec = new PercentEncodingCodec();
		StringBuilder signatureBaseStringBuilder = new StringBuilder();
		String method = request.getMethod();
		RequestMethod requestMethod = RequestMethod.of(method);
		signatureBaseStringBuilder.append(requestMethod != null ? requestMethod.toString() : codec.encode(method.toUpperCase())).append("&")
								  .append(codec.encode(generateBaseString(request))).append("&")
								  .append(codec.encode(normalizeParameters(request, credentials)));
		
	}
	
	private String generateBaseString(HttpRequestContext request) {
		StringBuilder baseStringBuilder = new StringBuilder();
		String scheme = request.getScheme().toLowerCase();
		int port = -1;
		String host = request.getHeader(HttpConstants.HEADER_HOST);
		if (host != null) {
			String[] hostAndPort = host.split(":");
			host = hostAndPort[0];
			if ("http".equals(scheme)) {
				port = 80;
			} else if ("https".equals(scheme)) {
				port = 443;
			}
			if (hostAndPort.length == 2) {
				port = Integer.parseInt(hostAndPort[1]);
			}
		}
		
		baseStringBuilder.append(scheme).append("://")
						 .append(host.toLowerCase());
		if (("http".equals(scheme) && (port > -1 && port != 80)) || ("https".equals(scheme) && (port > -1 && port != 443))) {
			baseStringBuilder.append(":").append(port);
		}
		
		baseStringBuilder.append(request.getPathInfo());
		return baseStringBuilder.toString();
	}
	
	private String normalizeParameters(HttpRequestContext request, OAuth1Credentials credentials) {
		HttpParameterCodec queryParameterCodec = new HttpQueryParameterCodec(new WWWFormURLEncodingCodec());
		
		Map<String, TreeSet<String>> map = new LinkedHashMap<>();
		String queryString = request.getQueryString();
		if (queryString != null && !queryString.isEmpty()) {
			Map<String, String[]> queryParameters = queryParameterCodec.decode(queryString);
			for (Entry<String, String[]> entry : queryParameters.entrySet()) {
				String key = entry.getKey();
				if (!map.containsKey(key)) {
					map.put(key, new TreeSet<String>());
				}
				
				for (String value : entry.getValue()) {
					map.get(key).add(value);
				}
			}
		}
		
		if (!map.containsKey(OAuth1Constants.OAUTH_CONSUMER_KEY)) map.put(OAuth1Constants.OAUTH_CONSUMER_KEY, new TreeSet<String>());
		map.get(OAuth1Constants.OAUTH_CONSUMER_KEY).add(credentials.getConsumerKey());
		
		if (!map.containsKey(OAuth1Constants.OAUTH_SIGNATURE_METHOD)) map.put(OAuth1Constants.OAUTH_SIGNATURE_METHOD, new TreeSet<String>());
		map.get(OAuth1Constants.OAUTH_SIGNATURE_METHOD).add(credentials.getSignatureMethod().toString());
		
		if (credentials.getTimestamp() != null) {
			if (!map.containsKey(OAuth1Constants.OAUTH_TIMESTAMP)) map.put(OAuth1Constants.OAUTH_TIMESTAMP, new TreeSet<String>());
			map.get(OAuth1Constants.OAUTH_TIMESTAMP).add(String.valueOf(credentials.getTimestamp()));
		}
		
		if (null != credentials.getNonce()) {
			if (!map.containsKey(OAuth1Constants.OAUTH_NONCE)) map.put(OAuth1Constants.OAUTH_NONCE, new TreeSet<String>());
			map.get(OAuth1Constants.OAUTH_NONCE).add(String.valueOf(credentials.getNonce()));
		}
		
		if (null != credentials.getVersion()) {
			if (!map.containsKey(OAuth1Constants.OAUTH_VERSION)) map.put(OAuth1Constants.OAUTH_VERSION, new TreeSet<String>());
			map.get(OAuth1Constants.OAUTH_VERSION).add(String.valueOf(credentials.getVersion()));
		}
		
		if (credentials instanceof OAuth1AuthenticatedCredentials) {
			OAuth1AuthenticatedCredentials authenticatedCredentials = (OAuth1AuthenticatedCredentials)credentials;
			if (!map.containsKey(OAuth1Constants.OAUTH_TOKEN)) map.put(OAuth1Constants.OAUTH_TOKEN, new TreeSet<String>());
			map.get(OAuth1Constants.OAUTH_TOKEN).add(String.valueOf(authenticatedCredentials.getToken()));
		} else if (credentials instanceof OAuth1TemporaryCredentials) {
			OAuth1TemporaryCredentials temporaryCredentials = (OAuth1TemporaryCredentials)credentials;
			if (!map.containsKey(OAuth1Constants.OAUTH_CALLBACK)) map.put(OAuth1Constants.OAUTH_CALLBACK, new TreeSet<String>());
			map.get(OAuth1Constants.OAUTH_CALLBACK).add(String.valueOf(temporaryCredentials.getCallback()));
		} else if (credentials instanceof OAuth1TokenCredentials) {
			OAuth1TokenCredentials tokenCredentials = (OAuth1TokenCredentials)credentials;
			if (!map.containsKey(OAuth1Constants.OAUTH_TOKEN)) map.put(OAuth1Constants.OAUTH_TOKEN, new TreeSet<String>());
			map.get(OAuth1Constants.OAUTH_TOKEN).add(String.valueOf(tokenCredentials.getToken()));
			
			if (!map.containsKey(OAuth1Constants.OAUTH_VERIFIER)) map.put(OAuth1Constants.OAUTH_VERIFIER, new TreeSet<String>());
			map.get(OAuth1Constants.OAUTH_VERIFIER).add(String.valueOf(tokenCredentials.getVerifier()));
		}
		
		ContentType contentType = null;
		String charsetName = null;
		if (request.containsHeader(HttpConstants.HEADER_CONTENT_TYPE)) {
			contentType = ContentType.from(request.getHeader(HttpConstants.HEADER_CONTENT_TYPE));
			charsetName = contentType.getParameters().get("charset");
		}
		
		String entityBody = null;
		try {
			entityBody = (charsetName == null || charsetName.isEmpty()) ? IOUtils.toString(request.getInputStream()) : IOUtils.toString(request.getInputStream(), charsetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			entityBody = null;
		}
		
		if (entityBody != null && contentType != null && "application/x-www-form-urlencoded".equals(contentType.getMediaTypeAsString())) {
			Map<String, String[]> queryParameters = queryParameterCodec.decode(entityBody);
			for (Entry<String, String[]> entry : queryParameters.entrySet()) {
				String key = entry.getKey();
				if (!map.containsKey(key)) {
					map.put(key, new TreeSet<String>());
				}
				
				for (String value : entry.getValue()) {
					map.get(key).add(value);
				}
			}
		}
		
		Map<String, String[]> resultMap = new LinkedHashMap<>();
		for (Entry<String, TreeSet<String>> entry : map.entrySet()) {
			TreeSet<String> treeSet = entry.getValue();
			resultMap.put(entry.getKey(), treeSet.toArray(new String[treeSet.size()]));
		}
		
		return new HttpQueryParameterCodec(new PercentEncodingCodec()).encode(resultMap);
	}
}
