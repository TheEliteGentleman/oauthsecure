/**
 * 
 */
package za.co.sindi.oauth.core.signers;

import za.co.sindi.oauth.core.exceptions.OAuthSignatureException;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public interface OAuthSigner {

	public String getSignatureMethod();
	public String sign(String baseString) throws OAuthSignatureException;
}
