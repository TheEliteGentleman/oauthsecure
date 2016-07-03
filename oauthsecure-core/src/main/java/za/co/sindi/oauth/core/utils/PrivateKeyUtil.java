/**
 * 
 */
package za.co.sindi.oauth.core.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import za.co.sindi.codec.Base64Codec;
import za.co.sindi.codec.exception.DecodingException;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public class PrivateKeyUtil {

	private PrivateKeyUtil() {}
	
	public static PrivateKey getPrivateKey(final String privateKeyFileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, DecodingException {
		File privateKeyFile = new File(privateKeyFileName);
		FileInputStream fis = new FileInputStream(privateKeyFile);
		DataInputStream dis = new DataInputStream(fis);
		byte[] privateKeyBytes = null;
		
		try {
			privateKeyBytes = new byte[(int) privateKeyFile.length()];
			dis.read(privateKeyBytes);
		} finally {
			dis.close();
			fis.close();
		}
		
		String BEGIN = "-----BEGIN PRIVATE KEY-----";
		String END = "-----END PRIVATE KEY-----";
		String str = new String(privateKeyBytes);
		if (str.contains(BEGIN) && str.contains(END)) {
			str = str.substring(BEGIN.length(), str.lastIndexOf(END));
		}

		//This works, but using a better approach.
//		KeyFactory fac = KeyFactory.getInstance("RSA");
//		EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(new Base64Codec().decode(str.getBytes()));
//		return fac.generatePrivate(privKeySpec);
		
		return createPrivateKey(str);
	}
	
	public static PrivateKey createPrivateKey(final String privateKeyString) throws NoSuchAlgorithmException, InvalidKeySpecException, DecodingException {
		KeyFactory factory = KeyFactory.getInstance("RSA");
//		byte[] privateKeyBytes = new Base64Codec().decode(privateKeyString.getBytes());
		byte[] privateKeyBytes = Base64Codec.getBase64Codec().decode(privateKeyString.getBytes());
		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		return factory.generatePrivate(privateKeySpec);
	}
}
