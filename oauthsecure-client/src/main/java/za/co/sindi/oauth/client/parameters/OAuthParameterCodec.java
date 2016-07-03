/**
 * 
 */
package za.co.sindi.oauth.client.parameters;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import za.co.sindi.codec.exception.DecodingException;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.encoding.PercentEncodingCodec;
import za.co.sindi.oauth.core.util.AbstractParameterCodec;

/**
 * @author Buhake Sindi
 * @since 07 March 2012
 *
 */
public abstract class OAuthParameterCodec extends AbstractParameterCodec<String> {

	/**
	 * @param startDelimiter
	 * @param endDelimiter
	 * @param pairDelimiter
	 * @param oauth1Encode
	 */
	protected OAuthParameterCodec(String startDelimiter, String endDelimiter, String pairDelimiter, boolean oauth1Encode) {
		super(startDelimiter, endDelimiter, pairDelimiter, oauth1Encode ? new PercentEncodingCodec() : null);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.core.util.ParameterEncoder#encode(java.util.Map)
	 */
	@Override
	public String encode(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		try {
			for (Entry<String, String> entry : parameters.entrySet()) {
				if (sb.length() > 0) {
					sb.append(pairDelimiter);
				}
				
				String key = encoding == null ? entry.getKey() : encoding.encode(entry.getKey());
				String value = encoding == null ? entry.getValue() : encoding.encode(entry.getValue());
				sb.append(key).append(startDelimiter).append(value).append(endDelimiter);
			}
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.core.util.ParameterDecoder#decode(java.lang.String)
	 */
	@Override
	public Map<String, String> decode(String value) {
		// TODO Auto-generated method stub
		if (value == null) {
			return null;
		}
		
		Map<String, String> parameters = new LinkedHashMap<>();
		try {
			value = value.trim();
			if (!value.isEmpty()) {
				int keyStartPos = 0;
				while (true) {
					int startDelimiterPos = value.indexOf(startDelimiter, keyStartPos + 1);
					if (startDelimiterPos < keyStartPos) break;
					int startValuePos = startDelimiterPos + startDelimiter.length();
					int endDelimiterPos = value.indexOf(endDelimiter, startValuePos);
					if (endDelimiterPos < startDelimiterPos) break;
					String key = value.substring(keyStartPos, startDelimiterPos);
					String _value = value.substring(startValuePos, endDelimiterPos);
					if (encoding != null) {
						_value = encoding.decode(_value);
					}
					parameters.put(key, _value);
					int pairDelimiterPos = value.indexOf(pairDelimiter, endDelimiterPos + endDelimiter.length());
					if (pairDelimiterPos < endDelimiterPos) break;
					keyStartPos = pairDelimiterPos + pairDelimiter.length();
				}
			}
		} catch (DecodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		return Collections.unmodifiableMap(parameters);
	}
}
