/**
 * 
 */
package za.co.sindi.oauth.client.response.oauth2;

import java.util.Iterator;

import net.sf.json.JSONObject;
import za.co.sindi.oauth.client.credentials.oauth2.AccessToken;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.core.utils.OAuth2Constants;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public class AccessTokenResponseHandler extends OAuth2ResponseHandler<AccessToken> {

	/* (non-Javadoc)
	 * @see net.oauth.client.response.oauth2.OAuth2ResponseHandler#handleOAuthResponse(net.sf.json.JSONObject)
	 */
	@Override
	protected AccessToken handleOAuthResponse(JSONObject json) throws OAuthResponseException {
		// TODO Auto-generated method stub
		if (json == null) {
			throw new OAuthResponseException("Entity body must contain a " + CONTENT_TYPE_JSON + " message.");
		}
		
		AccessToken accessToken = new AccessToken();
		for (@SuppressWarnings("unchecked")	Iterator<String> iter = json.keys(); iter.hasNext(); ) {
			String key = iter.next();
			String value = json.getString(key);
			
			if (OAuth2Constants.ACCESS_TOKEN.equals(key)) {
				accessToken.setAccessToken(value);
			} else if (OAuth2Constants.TOKEN_TYPE.equals(key)) {
				accessToken.setTokenType(value);
			} else if (OAuth2Constants.EXPIRES_IN.equals(key)) {
				accessToken.setExpiresIn(Integer.parseInt(value));
			} else if (OAuth2Constants.REFRESH_TOKEN.equals(key)) {
				accessToken.setRefreshToken(value);
			} else if (OAuth2Constants.SCOPE.equals(key)) {
				accessToken.setScope(value);
			} else if (OAuth2Constants.STATE.equals(key)) {
				accessToken.setState(value);
			} else {
				accessToken.setAdditionalParameter(key, value);
			}
		}
		
		return accessToken;
	}
}
