/**
 * 
 */
package za.co.sindi.oauth.core.util;

import za.co.sindi.codec.StringCodec;

/**
 * @author Bienfait Sindi
 * @since 18 January 2016
 *
 */
public abstract class HttpParameterCodec extends AbstractParameterCodec<String[]> {

	protected static final String[] EMTPY_STRING_ARRAY = new String[0];
	
	/**
	 * @param startDelimiter
	 * @param endDelimiter
	 * @param pairDelimiter
	 * @param encoding
	 */
	protected HttpParameterCodec(String startDelimiter, String endDelimiter, String pairDelimiter, StringCodec encoding) {
		super(startDelimiter, endDelimiter, pairDelimiter, encoding);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param startDelimiter
	 * @param endDelimiter
	 * @param pairDelimiter
	 */
	protected HttpParameterCodec(String startDelimiter, String endDelimiter, String pairDelimiter) {
		super(startDelimiter, endDelimiter, pairDelimiter);
		// TODO Auto-generated constructor stub
	}
}
