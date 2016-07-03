/**
 * 
 */
package za.co.sindi.oauth.core.algorithms.crypto.impl;

import za.co.sindi.oauth.core.algorithms.crypto.HMACCryptoHasher;

/**
 * This class computes RFC 2104-compliant HMAC-SHA1 signature.
 * 
 * @author Buhake Sindi
 * @since 19 March 2012
 *
 */
public class HMACSHA1CryptoHasher extends HMACCryptoHasher {

	/**
	 * <code>HmacSHA1</code> MAC algorithm.
	 */
	public HMACSHA1CryptoHasher() {
		super("HmacSHA1");
		// TODO Auto-generated constructor stub
	}
}
