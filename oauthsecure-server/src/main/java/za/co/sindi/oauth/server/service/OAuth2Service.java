/**
 * 
 */
package za.co.sindi.oauth.server.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import za.co.sindi.oauth.core.http.HttpConstants;
import za.co.sindi.oauth.core.utils.OAuth2Constants;
import za.co.sindi.oauth.server.context.RequestContext;
import za.co.sindi.oauth.server.context.ResponseContext;
import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.context.http.HttpResponseContext;
import za.co.sindi.oauth.server.exception.HttpException;
import za.co.sindi.oauth.server.exception.ServiceException;
import za.co.sindi.oauth.server.http.auth.AuthenticationSchemeBuilderFactory;
import za.co.sindi.oauth.server.http.auth.UsernamePasswordCredentials;
import za.co.sindi.oauth.server.http.auth.basic.BasicAuthenticationBuilderFactory;
import za.co.sindi.oauth.server.http.auth.basic.BasicChallengeAuthenticationScheme;
import za.co.sindi.oauth.server.http.auth.basic.BasicCredentialsAuthenticationScheme;
import za.co.sindi.oauth.server.provider.OAuth2DataProvider;
import za.co.sindi.oauth.server.result.Result;

/**
 * @author Bienfait Sindi
 * @since 10 October 2014
 *
 */
public abstract class OAuth2Service implements Service {

	private OAuth2DataProvider dataProvider;
	
	/**
	 * @return the dataProvider
	 */
	public OAuth2DataProvider getDataProvider() {
		return dataProvider;
	}

	/**
	 * @param dataProvider the dataProvider to set
	 */
	public void setDataProvider(OAuth2DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.service.Service#service(za.co.sindi.oauth.server.context.RequestContext, za.co.sindi.oauth.server.context.ResponseContext)
	 */
	@Override
	public Result service(RequestContext request, ResponseContext response) throws ServiceException {
		// TODO Auto-generated method stub
		if (request instanceof HttpRequestContext && response instanceof HttpResponseContext) {
			HttpRequestContext httpRequest = (HttpRequestContext) request;
			HttpResponseContext httpResponse = (HttpResponseContext) response;
			
			return service(httpRequest, httpResponse);
		}
		
		return null;
	}
	
	protected Result service(HttpRequestContext request, HttpResponseContext response) throws ServiceException {
		AuthenticationSchemeBuilderFactory<UsernamePasswordCredentials, BasicCredentialsAuthenticationScheme, BasicChallengeAuthenticationScheme> authenticationSchemeBuilderFactory = new BasicAuthenticationBuilderFactory();
		
		try {
			Map<String, String> clientCredentials = retrieveClientCredentials(request, authenticationSchemeBuilderFactory);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Map<String, String> retrieveClientCredentials(HttpRequestContext httpRequest, AuthenticationSchemeBuilderFactory<UsernamePasswordCredentials, BasicCredentialsAuthenticationScheme, BasicChallengeAuthenticationScheme> authenticationSchemeBuilderFactory) throws HttpException {
		Map<String, String> credentialsMap = new LinkedHashMap<>();
		boolean receivedAuthorization = httpRequest.containsHeader(HttpConstants.HEADER_AUTHORIZATION);
		if (receivedAuthorization) {
			UsernamePasswordCredentials credentials = authenticationSchemeBuilderFactory.createCredentialsAuthenticationBuilder(HttpConstants.HEADER_AUTHORIZATION).build().extractCredentials(httpRequest);
			credentialsMap.put(OAuth2Constants.CLIENT_ID, credentials.getUserPrincipal().getName());
			credentialsMap.put(OAuth2Constants.CLIENT_SECRET, credentials.getPassword());
		} else {
			String clientId = httpRequest.getParameter(OAuth2Constants.CLIENT_ID);
			String clientSecret = httpRequest.getParameter(OAuth2Constants.CLIENT_SECRET);
			if (clientId != null) {
				credentialsMap.put(OAuth2Constants.CLIENT_ID, clientId);
			}
			
			if (clientSecret != null) {
				credentialsMap.put(OAuth2Constants.CLIENT_SECRET, clientSecret);
			}
		}
		
		return Collections.unmodifiableMap(credentialsMap);
	}
}
