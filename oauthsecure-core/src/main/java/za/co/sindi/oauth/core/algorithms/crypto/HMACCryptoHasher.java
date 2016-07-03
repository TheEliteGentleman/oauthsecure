/**
 * 
 */
package za.co.sindi.oauth.core.algorithms.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * @author Buhake Sindi
 * @since 19 March 2012
 *
 */
public abstract class HMACCryptoHasher extends AbstractCryptoHasher<byte[]> {

	/**
	 * @param algorithm
	 */
	protected HMACCryptoHasher(String algorithm) {
		super(algorithm);
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.hash.CryptoHasher#sign(java.io.Serializable, byte[])
	 */
	/**
	 * Creates HMAC-SHA based on signature method.
	 * 
	 * @param key
	 * @param data
	 * @return 
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	@Override
	public byte[] sign(byte[] key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
		// TODO Auto-generated method stub
		if (key == null || key.length == 0) {
			throw new IllegalArgumentException("Key may not be null nor empty.");
		}
		
		if (data == null) {
			throw new IllegalArgumentException("Data may not be null.");
		}
		
		Mac mac = Mac.getInstance(getAlgorithm());
		mac.init(new SecretKeySpec(key, getAlgorithm()));
		return mac.doFinal(data);
	}
}
