/**
 * 
 */
package za.co.sindi.oauth.core.encoding;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;

import za.co.sindi.codec.BinaryDecoder;
import za.co.sindi.codec.BinaryEncoder;
import za.co.sindi.codec.StringCodec;
import za.co.sindi.codec.exception.DecodingException;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * @author Buhake Sindi
 * @since 14 January 2016
 *
 */
public class WWWFormURLEncodingCodec extends StringCodec implements BinaryEncoder, BinaryDecoder {

	private static final byte PERCENT_CHARACTER = '%';
	private static final int RADIX = 16;
	private static final BitSet WWW_FORM = new BitSet(256);
	private String charsetName;
	
	static {
		//DIGIT
		BitSet digit = new BitSet(256);
		digit.set('0');
		digit.set('1');
		digit.set('2');
		digit.set('3');
		digit.set('4');
		digit.set('5');
		digit.set('6');
		digit.set('7');
		digit.set('8');
		digit.set('9');
		
		//ALPHA
		BitSet alpha = new BitSet(256);
		alpha.set('a');
		alpha.set('b');
		alpha.set('c');
		alpha.set('d');
		alpha.set('e');
		alpha.set('f');
		alpha.set('g');
		alpha.set('h');
		alpha.set('i');
		alpha.set('j');
		alpha.set('k');
		alpha.set('l');
		alpha.set('m');
		alpha.set('n');
		alpha.set('o');
		alpha.set('p');
		alpha.set('q');
		alpha.set('r');
		alpha.set('s');
		alpha.set('t');
		alpha.set('u');
		alpha.set('v');
		alpha.set('w');
		alpha.set('x');
		alpha.set('y');
		alpha.set('z');
		alpha.set('A');
		alpha.set('B');
		alpha.set('C');
		alpha.set('D');
		alpha.set('E');
		alpha.set('F');
		alpha.set('G');
		alpha.set('H');
		alpha.set('I');
		alpha.set('J');
		alpha.set('K');
		alpha.set('L');
		alpha.set('M');
		alpha.set('N');
		alpha.set('O');
		alpha.set('P');
		alpha.set('Q');
		alpha.set('R');
		alpha.set('S');
		alpha.set('T');
		alpha.set('U');
		alpha.set('V');
		alpha.set('W');
		alpha.set('X');
		alpha.set('Y');
		alpha.set('Z');
		
		//SAFE
		BitSet safe = new BitSet(256);
		safe.set('-');
		safe.set('.');
		safe.set('_');
		safe.set('~');
		
		//SET
		WWW_FORM.or(digit);
		WWW_FORM.or(alpha);
		WWW_FORM.or(safe);
	}
	
	/**
	 * 
	 */
	public WWWFormURLEncodingCodec() {
		this(HttpConstants.CHARSET_UTF8);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param charsetName
	 */
	public WWWFormURLEncodingCodec(String charsetName) {
		super();
		this.charsetName = charsetName;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.codec.BinaryEncoder#encode(byte[])
	 */
	@Override
	public byte[] encode(byte[] data) throws EncodingException {
		// TODO Auto-generated method stub
		if (data == null || data.length == 0) {
			return data;
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (byte c : data) {
			if(WWW_FORM.get(c)) {
				baos.write(c);
			} else if (c == ' ') {
				baos.write('+');
			} else {
				baos.write(PERCENT_CHARACTER);
				baos.write(Character.toUpperCase(Character.forDigit((c >> 4) & 0xF, RADIX)));
				baos.write(Character.toUpperCase(Character.forDigit(c & 0xF, RADIX)));
			}
		}
		
		return baos.toByteArray();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.codec.BinaryDecoder#decode(byte[])
	 */
	@Override
	public byte[] decode(byte[] data) throws DecodingException {
		// TODO Auto-generated method stub
		if (data == null || data.length == 0) {
			return data;
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int length = data.length;
		for (int i = 0; i < length; i++) {
			if(data[i] == PERCENT_CHARACTER) {
				if ((i + 2) >= length) {
					throw new IllegalArgumentException("Invalid % encoding sequence on index " + i);
				} 

				int digit1 = Character.digit(data[i + 1], RADIX);
				int digit2 = Character.digit(data[i + 2], RADIX);
				
				if (digit1 == -1) {
					throw new IllegalArgumentException("Invalid character '" + data[i + 1] + "' on index " + (i + 1));
				}
				
				if (digit2 == -1) {
					throw new IllegalArgumentException("Invalid character '" + data[i + 2] + "' on index " + (i + 2));
				}
				
				baos.write(((digit1 << 4) | digit2) & 0xFF);
				i += 2;
			} else if (data[i] == '+') {
				baos.write(' ');
			} else {
				baos.write(data[i]);
			}
		}
	
		return baos.toByteArray();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.codec.StringDecoder#decode(java.lang.String)
	 */
	@Override
	public String decode(String data) throws DecodingException {
		// TODO Auto-generated method stub
		try {
			return new String(decode(data.getBytes(charsetName)), charsetName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new DecodingException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.codec.StringEncoder#encode(java.lang.String)
	 */
	@Override
	public String encode(String data) throws EncodingException {
		// TODO Auto-generated method stub
		try {
			return new String(encode(data.getBytes(charsetName)), charsetName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new EncodingException(e);
		}
	}
}
