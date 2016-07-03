/**
 * 
 */
package za.co.sindi.oauth.client.parameters.oauth1;

import za.co.sindi.oauth.client.parameters.OAuthParameterCodec;

/**
 * @author Buhake Sindi
 * @since 11 February 2012
 *
 */
public abstract class OAuth1ParameterCodec extends OAuthParameterCodec {

	/**
	 * @param pairDelimiter
	 */
	protected OAuth1ParameterCodec(String pairDelimiter) {
		super("=\"", "\"", pairDelimiter, true);
		// TODO Auto-generated constructor stub
	}
}
