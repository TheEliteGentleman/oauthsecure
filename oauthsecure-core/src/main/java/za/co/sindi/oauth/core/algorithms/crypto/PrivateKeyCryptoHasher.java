/**
 * 
 */
package za.co.sindi.oauth.core.algorithms.crypto;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;

/**
 * @author Buhake Sindi
 * @since 19 March 2012
 *
 */
public abstract class PrivateKeyCryptoHasher extends AbstractCryptoHasher<PrivateKey> {

	/**
	 * @param algorithm
	 */
	protected PrivateKeyCryptoHasher(String algorithm) {
		super(algorithm);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.algorithm.crypto.CryptoHasher#sign(java.io.Serializable, byte[])
	 */
	@Override
	public byte[] sign(PrivateKey privateKey, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException, GeneralSecurityException {
		// TODO Auto-generated method stub
		if (privateKey == null) {
			throw new IllegalArgumentException("Private Key may not be null.");
		}
		
		if (data == null) {
			throw new IllegalArgumentException("Data may not be null.");
		}
		
		Signature signature = Signature.getInstance(getAlgorithm());
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}
}
