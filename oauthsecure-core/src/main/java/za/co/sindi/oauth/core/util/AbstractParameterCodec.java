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
public abstract class AbstractParameterCodec<V> implements ParameterEncoder<V>, ParameterDecoder<V> {

	protected String startDelimiter;
	protected String endDelimiter;
	protected String pairDelimiter;
	protected StringCodec encoding;
	
	/**
	 * @param startDelimiter
	 * @param endDelimiter
	 * @param pairDelimiter
	 */
	protected AbstractParameterCodec(String startDelimiter, String endDelimiter, String pairDelimiter) {
		this(startDelimiter, endDelimiter, pairDelimiter, null);
	}
	
	/**
	 * @param startDelimiter
	 * @param endDelimiter
	 * @param pairDelimiter
	 * @param encoding
	 */
	protected AbstractParameterCodec(String startDelimiter, String endDelimiter, String pairDelimiter, StringCodec encoding) {
		super();
		this.startDelimiter = startDelimiter;
		this.endDelimiter = endDelimiter;
		this.pairDelimiter = pairDelimiter;
		this.encoding = encoding;
	}
}
