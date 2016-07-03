/**
 * 
 */
package za.co.sindi.oauth.core.algorithms.crypto;

import java.io.Serializable;

/**
 * @author Buhake Sindi
 * @since 19 March 2012
 *
 */
public abstract class AbstractCryptoHasher<K extends Serializable> implements CryptoHasher<K> {

	private final String algorithm;

	/**
	 * @param algorithm
	 */
	protected AbstractCryptoHasher(String algorithm) {
		super();
		this.algorithm = algorithm;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.algorithm.crypto.CryptoHasher#getAlgorithm()
	 */
	@Override
	public final String getAlgorithm() {
		// TODO Auto-generated method stub
		return algorithm;
	}
}
