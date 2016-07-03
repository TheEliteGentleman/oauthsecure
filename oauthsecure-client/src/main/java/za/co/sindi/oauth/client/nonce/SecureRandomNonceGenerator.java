/**
 * 
 */
package za.co.sindi.oauth.client.nonce;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.log4j.Logger;

import za.co.sindi.oauth.client.NonceGenerator;

/**
 * @author Buhake Sindi
 * @since 09 February 2012
 *
 */
public class SecureRandomNonceGenerator implements NonceGenerator {

	private static final Logger logger = Logger.getLogger(SecureRandomNonceGenerator.class);
	private static SecureRandom secureRandom;
	
	static {
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see net.oauth.client.NonceGenerator#generateNonce()
	 */
	@Override
	public String generateNonce() {
		// TODO Auto-generated method stub
//			byte[] bytes = new byte[32];
//			secureRandom.nextBytes(bytes);
//			Base64Codec base64 = new Base64Codec();
//			nonce = new String(base64.encode(bytes));
		return Long.toHexString(Math.abs(secureRandom.nextLong()));
	}
}
