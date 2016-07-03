/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.entity;

import java.io.UnsupportedEncodingException;

import za.co.sindi.codec.StringCodec;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.client.transport.http.AbstractHttpEntity;
import za.co.sindi.oauth.client.transport.http.HttpParameter;
import za.co.sindi.oauth.client.transport.http.HttpParameters;
import za.co.sindi.oauth.core.encoding.WWWFormURLEncodingCodec;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public class HttpParameterEntity extends AbstractHttpEntity {

	/**
	 * @param parameters
	 * @param charset
	 * @throws UnsupportedEncodingException 
	 * @throws EncodingException 
	 */
	public HttpParameterEntity(HttpParameters parameters, String charset) throws UnsupportedEncodingException, EncodingException {
		super("application/x-www-form-urlencoded");
		// TODO Auto-generated constructor stub
		if (parameters == null || charset == null) {
			throw new IllegalArgumentException();
		}
		
		setCharset(charset);
		StringCodec codec = new WWWFormURLEncodingCodec(charset);
		StringBuilder sb = new StringBuilder();
		for (HttpParameter parameter : parameters.getParameters()) {
			for (String value: parameter.getValues()) {
				if (sb.length() > 0 ) {
					sb.append("&");
				}
				
				sb.append(codec.encode(parameter.getName())).append("=").append(codec.encode(value));
			}
		}
		
		setContent(sb.toString().getBytes(charset));
	}
}
