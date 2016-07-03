/**
 * 
 */
package za.co.sindi.oauth.core.signers;

import za.co.sindi.codec.StringCodec;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.encoding.PercentEncodingCodec;
import za.co.sindi.oauth.core.exceptions.OAuthSignatureException;
import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public class OAuthPlaintextSigner extends OAuthTokenSecretSigner {

	/**
	 * @param consumerSecret
	 * @param tokenSecret
	 */
	public OAuthPlaintextSigner(String consumerSecret, String tokenSecret) {
		super(consumerSecret, tokenSecret);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.oauth1.signer.OAuthSigner#getSignatureMethod()
	 */
	@Override
	public String getSignatureMethod() {
		// TODO Auto-generated method stub
		return OAuth1Constants.SIGNATURE_PLAINTEXT;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.oauth1.signer.OAuthSigner#sign(java.lang.String)
	 */
	@Override
	public String sign(String data) throws OAuthSignatureException {
		// TODO Auto-generated method stub
		StringCodec codec = new PercentEncodingCodec();
		try {
			return codec.encode(getConsumerSecret()) + "&" + codec.encode(((getTokenSecret() == null || getTokenSecret().isEmpty()) ? "" : getTokenSecret()));
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthSignatureException(e);
		}
	}
}
