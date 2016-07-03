/**
 * 
 */
package za.co.sindi.oauth.client.response.oauth1;

import java.util.Map;

import za.co.sindi.oauth.client.credentials.oauth1.AccessToken;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.parameters.oauth1.OAuth1QueryParameterCodec;
import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * @author Buhake Sindi
 * @since 09 February 2012
 *
 */
public class AccessTokenResponseHandler extends OAuth1ResponseHandler<AccessToken> {

	/**
	 * 
	 */
	public AccessTokenResponseHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.response.oauth1.OAuth1ResponseHandler#handleResponse(java.util.Map)
	 */
	@Override
	protected AccessToken handleOAuthResponse(Map<String, String> oauthParameters) throws OAuthResponseException {
		// TODO Auto-generated method stub
		AccessToken accessToken = null;
		
		if (oauthParameters != null && !oauthParameters.isEmpty()) {
			if (!oauthParameters.containsKey(OAuth1Constants.OAUTH_TOKEN) && 
				!oauthParameters.containsKey(OAuth1Constants.OAUTH_TOKEN_SECRET)) {
				throw new OAuthResponseException("Invalid OAuth access response (" + new OAuth1QueryParameterCodec().encode(oauthParameters) + ")");
			}
			
			accessToken = new AccessToken();
			accessToken.setToken(oauthParameters.get(OAuth1Constants.OAUTH_TOKEN));
			accessToken.setTokenSecret(oauthParameters.get(OAuth1Constants.OAUTH_TOKEN_SECRET));
		}
		
		return accessToken;
	}
}
