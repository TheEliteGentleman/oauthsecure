/**
 * 
 */
package za.co.sindi.oauth.client.response.oauth1;

import java.util.Map;

import za.co.sindi.oauth.client.credentials.oauth1.RefreshToken;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.parameters.oauth1.OAuth1QueryParameterCodec;
import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * @author Buhake Sindi
 * @since 09 February 2012
 *
 */
public class RequestTokenResponseHandler extends OAuth1ResponseHandler<RefreshToken> {

	/**
	 * 
	 */
	public RequestTokenResponseHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.response.oauth1.OAuth1ResponseHandler#handleOAuthResponse(java.util.Map)
	 */
	@Override
	protected RefreshToken handleOAuthResponse(Map<String, String> oauthParameters) throws OAuthResponseException {
		// TODO Auto-generated method stub
		RefreshToken requestToken = null;
		
		if (oauthParameters != null && !oauthParameters.isEmpty()) {
			if (!oauthParameters.containsKey(OAuth1Constants.OAUTH_TOKEN) && 
				!oauthParameters.containsKey(OAuth1Constants.OAUTH_TOKEN_SECRET) &&
				!oauthParameters.containsKey(OAuth1Constants.OAUTH_CALLBACK_CONFIRMED)) {
				throw new OAuthResponseException("Invalid OAuth response (" + new OAuth1QueryParameterCodec().encode(oauthParameters) + ")");
			}
			
			requestToken = new RefreshToken();
			requestToken.setToken(oauthParameters.get(OAuth1Constants.OAUTH_TOKEN));
			requestToken.setTokenSecret(oauthParameters.get(OAuth1Constants.OAUTH_TOKEN_SECRET));
			requestToken.setCallbackConfirmed(Boolean.valueOf(oauthParameters.get(OAuth1Constants.OAUTH_CALLBACK_CONFIRMED)));
		}
		
		return requestToken;
	}
}
