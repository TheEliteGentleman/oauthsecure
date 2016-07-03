/**
 * 
 */
package za.co.sindi.oauth.core.signers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import za.co.sindi.codec.exception.DecodingException;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.exceptions.OAuthSignatureException;
import za.co.sindi.oauth.core.utils.OAuth1Constants;
import za.co.sindi.oauth.core.utils.OAuthSignatureUtil;
import za.co.sindi.oauth.core.utils.PrivateKeyUtil;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public class OAuthRsaSha1Signer implements OAuthSigner {

	private PrivateKey privateKey;
	
	/**
	 * @param privateKey
	 */
	public OAuthRsaSha1Signer(PrivateKey privateKey) {
		super();
		this.privateKey = privateKey;
	}
	
	public OAuthRsaSha1Signer(final String privateKeyString) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, DecodingException {
		if (privateKeyString == null || privateKeyString.isEmpty()) {
			throw new IllegalArgumentException("Private key string cannot be null nor can it be empty.");
		}
		
		privateKey = PrivateKeyUtil.createPrivateKey(privateKeyString);
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.oauth1.signer.OAuthSigner#getSignatureMethod()
	 */
	@Override
	public String getSignatureMethod() {
		// TODO Auto-generated method stub
		return OAuth1Constants.SIGNATURE_RSA_SHA1;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.oauth1.signer.OAuthSigner#sign(java.lang.String)
	 */
	@Override
	public String sign(String data) throws OAuthSignatureException {
		// TODO Auto-generated method stub
		try {
			return OAuthSignatureUtil.generateRSASHA1Signature(privateKey, data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthSignatureException(e);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			throw new OAuthSignatureException(e);
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthSignatureException(e);
		}
	}
}
