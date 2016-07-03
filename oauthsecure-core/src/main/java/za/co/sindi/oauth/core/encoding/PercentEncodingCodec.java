/**
 * 
 */
package za.co.sindi.oauth.core.encoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import za.co.sindi.codec.BinaryDecoder;
import za.co.sindi.codec.BinaryEncoder;
import za.co.sindi.codec.StringCodec;
import za.co.sindi.codec.exception.DecodingException;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * Implemented Percent Encoding according to RFC 5849, paragraph 3.6
 * <br />This encoding/decoding differs from the specification found in RFC 3986, paragraph 2.1.
 * 
 * @author Buhake Sindi
 * @since 03 February 2012
 *
 */
public class PercentEncodingCodec extends StringCodec implements BinaryEncoder, BinaryDecoder /* Encoding */ {
	
	private static final String DEFAULT_CHARSET = HttpConstants.CHARSET_UTF8;
	
//	private static final char[] UNRESERVED_CHARACTERS = {
//		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
//		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' ,'-', '.', '_', '~'
//	};
	
	private static final byte[] HEXADECIMAL_DIGITS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
	};
	
	private static final char PERCENT_CHARACTER = '%';
	
	private String charsetName;

	/**
	 * 
	 */
	public PercentEncodingCodec() {
		this(DEFAULT_CHARSET);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param charsetName
	 */
	public PercentEncodingCodec(String charsetName) {
		super();
		this.charsetName = charsetName;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.encoding.Encoding#encode(java.lang.String)
	 */
//	@Override
	public String encode(String s) throws EncodingException {
		// TODO Auto-generated method stub
		try {
			return new String(encode(s.getBytes(charsetName)), charsetName);
//			return new String(encode(s.toCharArray()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new EncodingException(e);
		}
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.encoding.Encoding#decode(java.lang.String)
	 */
//	@Override
	public String decode(String s) throws DecodingException {
		// TODO Auto-generated method stub
		try {
			return new String(decode(s.getBytes(charsetName)), charsetName);
//			return new String(decode(s.toCharArray()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new DecodingException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.neurologic.codec.Decoder#decode(byte[])
	 */
//	@Override
	public byte[] decode(byte[] data) throws DecodingException {
		// TODO Auto-generated method stub
		if (data == null || data.length == 0) {
			return data;
		}
		
//		CharArrayWriter caw = new CharArrayWriter();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int i = 0; i < data.length; i++) {
			byte c = data[i];
			int index = i;
			if(c == PERCENT_CHARACTER) {
//				caw.write(toCharacter(data[++i], data[++i], index));
				baos.write(toCharacter(data[++i], data[++i], index));
			} else {
//				caw.write(c);
				baos.write(c);
			}
		}
	
//		return caw.toCharArray();
		return baos.toByteArray();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.codec.Encoder#encode(byte[])
	 */
//	@Override
	public byte[] encode(byte[] data) throws EncodingException {
		// TODO Auto-generated method stub
		if (data == null || data.length == 0) {
			return data;
		}
		
//		CharArrayWriter caw = new CharArrayWriter();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			for (byte c : data) {
				if((c >= 0x30 && c <= 0x39) //DIGITS [0..9] 
				 ||(c >= 0x41 && c <= 0x5A) //CHARACTERS ['A'..'Z']
				 ||(c >= 0x61 && c <= 0x7A) //CHARACTERS ['a'..'z']
				 /*||(c == 0x20)*/ //SPACE MUST BE ENCODED
				 ||(c == 0x2D) //HYPHEN
				 ||(c == 0x2E) //PERIOD
				 ||(c == 0x5F) //UNDERSCORE
				 ||(c == 0x7E) //TILDE
				) {
//					caw.write(c);
					baos.write(c);
				} else {
//					caw.write(PERCENT_CHARACTER);
//					caw.write(toHexDigits(c));
					baos.write(PERCENT_CHARACTER);
					baos.write(toHexDigits(c));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// This is actually never thrown....just catching nothing really.
			throw new RuntimeException(e);
		}
		
//		return caw.toCharArray();
		return baos.toByteArray();
	}
	
	private byte toCharacter(byte char1, byte char2, int index) throws DecodingException {
		int digit1 = Character.digit(char1, 16);
		int digit2 = Character.digit(char2, 16);
		
		if (digit1 == -1) {
			throw new DecodingException("Invalid character '" + char1 + "' on index " + (index + 1));
		}
		
		if (digit2 == -1) {
			throw new DecodingException("Invalid character '" + char2 + "' on index " + (index + 2));
		}
		
		return (byte) (((digit1 << 4) | digit2) & 0xFF);
	}
	
	private byte[] toHexDigits(byte c) {
		byte[] hexDigit = new byte[2];
		hexDigit[0] = HEXADECIMAL_DIGITS[(c & 0xF0) >>> 4];
		hexDigit[1] = HEXADECIMAL_DIGITS[(c & 0x0F)];
		
		return hexDigit;
	}
}
