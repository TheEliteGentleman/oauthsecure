/**
 * 
 */
package za.co.sindi.oauth.core.algorithms.crypto.impl;

import za.co.sindi.oauth.core.algorithms.crypto.PrivateKeyCryptoHasher;

/**
 * This class computes RSA-SHA1 signature.
 * 
 * @author Buhake Sindi
 * @since 19 March 2012
 *
 */
public class RSASHA1CryptoHasher extends PrivateKeyCryptoHasher {

	/**
	 * 
	 */
	public RSASHA1CryptoHasher() {
		super("SHA1withRSA");
		// TODO Auto-generated constructor stub
	}
}
