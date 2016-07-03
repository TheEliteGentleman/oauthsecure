/**
 * 
 */
package za.co.sindi.oauth.client.parameters.oauth2;

import za.co.sindi.oauth.client.parameters.OAuthParameterCodec;

/**
 * @author Buhake Sindi
 * @since 11 February 2012
 *
 */
public abstract class OAuth2ParameterCodec extends OAuthParameterCodec {

	/**
	 * @param pairDelimiter
	 */
	protected OAuth2ParameterCodec(String pairDelimiter) {
		super("=\"", "\"", pairDelimiter, false);
		// TODO Auto-generated constructor stub
	}
}
