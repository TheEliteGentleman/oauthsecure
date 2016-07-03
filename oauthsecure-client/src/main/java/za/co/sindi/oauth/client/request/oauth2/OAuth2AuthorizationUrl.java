/**
 * 
 */
package za.co.sindi.oauth.client.request.oauth2;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.parameters.oauth2.OAuth2Parameters;
import za.co.sindi.oauth.client.parameters.oauth2.OAuth2QueryParameterCodec;
import za.co.sindi.oauth.client.request.AbstractAuthorizationUrl;
import za.co.sindi.oauth.client.request.oauth2.enums.ResponseType;
import za.co.sindi.oauth.core.utils.OAuth2Constants;
import za.co.sindi.oauth.util.OAuth2Util;

/**
 * @author Buhake Sindi
 * @since 17 February 2012
 *
 */
public class OAuth2AuthorizationUrl extends AbstractAuthorizationUrl {

	private ResponseType responseType;
	private String clientId;
	private URI redirectUri;
	private String scope;
	private String state;
	
	/**
	 * 
	 * @param authorizationUrl
	 * @param responseType
	 */
	public OAuth2AuthorizationUrl(final String authorizationUrl, final ResponseType responseType) {
		super(authorizationUrl);
		
		if (responseType == null) {
			throw new IllegalArgumentException(OAuth2Constants.RESPONSE_TYPE + " is required.");
		}
		
		this.responseType = responseType;
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthAuthorizationRequest#generateUrl()
	 */
	@Override
	public String generateUrl() throws OAuthRequestException {
		// TODO Auto-generated method stub
		if (clientId == null || clientId.isEmpty()) {
			throw new IllegalArgumentException(OAuth2Constants.CLIENT_ID + " is required.");
		}
		
		OAuth2Parameters parameters = new OAuth2Parameters();
		
		try {
			parameters.setResponseType(responseType.toString());
			parameters.setClientId(clientId);
			
			if (redirectUri != null) {
				parameters.setRedirectUri(URLEncoder.encode(redirectUri.toString(), "UTF-8"));
			}
			
			if (scope != null && !scope.isEmpty()) {
				parameters.setScope(scope);
			}
			
			if (state != null && !state.isEmpty()) {
				parameters.setState(state);
			} else {
				if (logger.isInfoEnabled()) {
					logger.warn(OAuth2Constants.STATE + " is RECOMMENDED (See section 4.1.1 of OAuth 2 specification in RFC 6749).");
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthRequestException(e);
		}
		
		String url = OAuth2Util.normalizeUrl(getAuthorizationUrl());
		int queryPos = url.indexOf('?');
		if (queryPos > -1) {
			url += "&";
		} else {
			url += "?";
		}
		
		return url + new OAuth2QueryParameterCodec().encode(parameters.toMap());
	}

	/**
	 * @param clientId the clientId to set
	 */
	public OAuth2AuthorizationUrl setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	/**
	 * @param redirectUri the redirectUri to set
	 */
	public OAuth2AuthorizationUrl setRedirectUri(String redirectUri) {
//		try {
//			this.redirectUri = new URI(redirectUri);
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			logger.error(e.getLocalizedMessage(), e);
//			throw new IllegalArgumentException(e);
//		}
		this.redirectUri = URI.create(redirectUri);
		return this;
	}

	/**
	 * @param scope the scope to set
	 */
	public OAuth2AuthorizationUrl setScope(String scope) {
		this.scope = scope;
		return this;
	}

	/**
	 * @param state the state to set
	 */
	public OAuth2AuthorizationUrl setState(String state) {
		this.state = state;
		return this;
	}
}
