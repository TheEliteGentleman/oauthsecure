/**
 * 
 */
package za.co.sindi.oauth.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

import za.co.sindi.codec.Base64Codec;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.algorithms.crypto.impl.HMACSHA1CryptoHasher;
import za.co.sindi.oauth.core.algorithms.crypto.impl.HMACSHA256CryptoHasher;
import za.co.sindi.oauth.core.algorithms.crypto.impl.RSASHA1CryptoHasher;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public final class OAuthSignatureUtil {
	
	private OAuthSignatureUtil() {}
	
	/**
	 * Base 64 Encoding.
	 * 
	 * @param data
	 * @param key
	 * @param method
	 * @return
	 * @throws GeneralSecurityException
	 * @throws EncodingException 
	 */
	public static String base64Encode(byte[] data) throws GeneralSecurityException, EncodingException {
//		return new String(new Base64Codec().encode(data));
		return new String(Base64Codec.getBase64Codec().encode(data));
	}
	
	/**
	 * Computes RFC 2104-compliant HMAC signature.
	 * 
	 * @param key
	 *            - the signing key.
	 * @param data
	 *            - the data to be signed.
	 * @return The Base64-encoded RFC 2104-compliant HMAC signature.
	 * @throws EncodingException 
	 * @throws java.security.SignatureException
	 *             when signature generation fails
	 */
	public static String generateHmacSha1Signature(byte[] key, byte[] data) throws UnsupportedEncodingException, GeneralSecurityException, EncodingException {
		return base64Encode(new HMACSHA1CryptoHasher().sign(key, data));
	}
	
	/**
	 * Computes RFC 2104-compliant HMAC-256 signature.
	 * 
	 * @param key
	 *            - the signing key.
	 * @param data
	 *            - the data to be signed.
	 * @return The Base64-encoded RFC 2104-compliant HMAC signature.
	 * @throws EncodingException 
	 * @throws java.security.SignatureException
	 *             when signature generation fails
	 */
	public static String generateHmacSha256Signature(byte[] key, byte[] data) throws GeneralSecurityException, EncodingException {
		return base64Encode(new HMACSHA256CryptoHasher().sign(key, data));
	}
	
	/**
	 * Computes RSA-SHA1 signature.
	 * 
	 * @param data
	 *            - the data to be signed.
	 * @param key
	 *            - the signing key.
	 * @return The Base64-encoded RFC 2104-compliant HMAC signature.
	 * @throws EncodingException 
	 * @throws UnsupportedEncodingException 
	 * @throws java.security.SignatureException
	 *             when signature generation fails
	 */
	public static String generateRSASHA1Signature(PrivateKey privateKey, byte[] data) throws GeneralSecurityException, EncodingException {
		return base64Encode(new RSASHA1CryptoHasher().sign(privateKey, data));
	}
}
