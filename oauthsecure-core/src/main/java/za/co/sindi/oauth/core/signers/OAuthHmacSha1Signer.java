/**
 * 
 */
package za.co.sindi.oauth.core.signers;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import za.co.sindi.codec.StringCodec;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.encoding.PercentEncodingCodec;
import za.co.sindi.oauth.core.exceptions.OAuthSignatureException;
import za.co.sindi.oauth.core.utils.OAuth1Constants;
import za.co.sindi.oauth.core.utils.OAuthSignatureUtil;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public class OAuthHmacSha1Signer extends OAuthTokenSecretSigner {

	private static final String UTF_8 = "UTF-8";
	
	/**
	 * @param consumerSecret
	 * @param tokenSecret
	 */
	public OAuthHmacSha1Signer(String consumerSecret, String tokenSecret) {
		super(consumerSecret, tokenSecret);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.oauth1.signer.OAuthSigner#getSignatureMethod()
	 */
	@Override
	public String getSignatureMethod() {
		// TODO Auto-generated method stub
		return OAuth1Constants.SIGNATURE_HMAC_SHA1;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.oauth1.signer.OAuthSigner#sign(java.lang.String)
	 */
	@Override
	public String sign(String baseString) throws OAuthSignatureException {
		// TODO Auto-generated method stub
		try {
			StringCodec codec = new PercentEncodingCodec();
			String key = codec.encode(getConsumerSecret()) + "&" + codec.encode(getTokenSecret() == null ? "" : getTokenSecret());
			return OAuthSignatureUtil.generateHmacSha1Signature(key.getBytes(UTF_8), baseString.getBytes(UTF_8));
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
