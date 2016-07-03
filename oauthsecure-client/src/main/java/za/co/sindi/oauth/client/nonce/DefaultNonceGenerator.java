/**
 * 
 */
package za.co.sindi.oauth.client.nonce;

import za.co.sindi.oauth.client.NonceGenerator;

/**
 * @author Buhake Sindi
 * @since 09 February 2012
 *
 */
public class DefaultNonceGenerator implements NonceGenerator {

	/* (non-Javadoc)
	 * @see net.oauth.client.NonceGenerator#generateNonce()
	 */
	@Override
	public String generateNonce() {
		// TODO Auto-generated method stub
		return String.valueOf(System.nanoTime());
	}
}
