/**
 * 
 */
package za.co.sindi.oauth.core.algorithms.crypto;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Buhake Sindi
 * @since 19 March 2012
 *
 */
public interface CryptoHasher<K extends Serializable> {

	public String getAlgorithm();
	public byte[] sign(K key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException, GeneralSecurityException;
}
