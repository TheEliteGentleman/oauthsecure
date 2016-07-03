package za.co.sindi.oauth.client.request.oauth1;

import java.util.Map;

import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.request.AbstractAuthorizationUrl;
import za.co.sindi.oauth.client.transport.http.HttpParameters;
import za.co.sindi.oauth.core.http.HttpQueryParameterCodec;
import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * 
 * @author Buhake Sindi
 * @since 02 February 2012
 *
 */
public final class OAuth1AuthorizationUrl extends AbstractAuthorizationUrl {
	
	private String token;
	private Map<String, String[]> additionalParameters;

	/**
	 * @param authorizationUrl
	 */
	public OAuth1AuthorizationUrl(final String authorizationUrl) {
		super(authorizationUrl);
	}
	
	/**
	 * @param token the refresh token to set
	 */
	public OAuth1AuthorizationUrl setToken(String token) {
		this.token = token;
		return this;
	}

	/**
	 * @param additionalParameters the additionalParameters to set
	 */
	public OAuth1AuthorizationUrl setAdditionalParameters(Map<String, String[]> additionalParameters) {
		this.additionalParameters = additionalParameters;
		return this;
	}
	
	/**
	 * @param additionalParameters the additionalParameters to set
	 */
	public OAuth1AuthorizationUrl setAdditionalParameters(HttpParameters additionalParameters) {
		if (additionalParameters != null && !additionalParameters.isEmpty()) {
			setAdditionalParameters(additionalParameters.toMap());
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthAuthorizationRequest#generateUrl()
	 */
	@Override
	public String generateUrl() throws OAuthRequestException{
		// TODO Auto-generated method stub
		if (token == null || token.isEmpty()) {
			throw new OAuthRequestException("A refresh token is required.");
		}
		
		String authorizationUrl = getAuthorizationUrl();
		String url = authorizationUrl;
		String fragment = null;
		
		int fragmentPos = authorizationUrl.indexOf('#', 8);
		if (fragmentPos > -1) {
			fragment = authorizationUrl.substring(fragmentPos + 1);
			url = authorizationUrl.substring(0, fragmentPos);
		}
		
		StringBuilder sb = new StringBuilder(url);
		int requestPos = authorizationUrl.indexOf('?');
		if (requestPos > -1) {
			sb.append('&');
		} else {
			sb.append('?');
		}
		
		sb.append(OAuth1Constants.OAUTH_TOKEN).append("=").append(token);
		
		if (additionalParameters != null) {
			sb.append("&").append(new HttpQueryParameterCodec().encode(additionalParameters));
		}
		
		//Fragment
		if (fragment != null && !fragment.isEmpty()) {
			sb.append("#").append(fragment);
		}
		
		return sb.toString();
	}
}