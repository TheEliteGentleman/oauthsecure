/**
 * 
 */
package za.co.sindi.oauth.core.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import za.co.sindi.codec.StringCodec;
import za.co.sindi.codec.exception.DecodingException;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.core.util.HttpParameterCodec;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public class HttpQueryParameterCodec extends HttpParameterCodec {

	/**
	 * 
	 */
	public HttpQueryParameterCodec() {
		this(null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param encoding
	 */
	public HttpQueryParameterCodec(StringCodec encoding) {
		super("=", "", "&", encoding);
		// TODO Auto-generated constructor stub
	}
	

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.core.util.ParameterEncoder#encode(java.util.Map)
	 */
	@Override
	public String encode(Map<String, String[]> parameters) {
		// TODO Auto-generated method stub
		if (parameters == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		try {
			for (Entry<String, String[]> entry : parameters.entrySet()) {
				String key = entry.getKey();
				
				for (String value : entry.getValue()) {
					if (sb.length() > 0) {
						sb.append(pairDelimiter);
					}
					
					sb.append(encoding != null ? encoding.encode(key) : key).append(startDelimiter).append(encoding != null ? encoding.encode(value) : value).append(endDelimiter);
				}
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
	public Map<String, String[]> decode(String value) {
		// TODO Auto-generated method stub
		if (value == null) {
			return null;
		}
		
		Map<String, String[]> parameters = new LinkedHashMap<>();
		try {
			value = value.trim();
			if (!value.isEmpty()) {
				int keyStartPos = 0;
				while (true) {
					int pairDelimiterPos = value.indexOf(pairDelimiter, keyStartPos + 1);
					int startDelimiterPos = value.lastIndexOf(startDelimiter, pairDelimiterPos);
					String key = null;
					if (pairDelimiterPos < keyStartPos) {
						key = value.substring(keyStartPos);
					} else if (startDelimiterPos < keyStartPos) {
						key = value.substring(keyStartPos, pairDelimiterPos);
					} else {
						key = value.substring(keyStartPos, startDelimiterPos);
					}
					if (startDelimiterPos < keyStartPos) {
						if (!parameters.containsKey(key)) {
							parameters.put(key, EMTPY_STRING_ARRAY);
						}
					} else {
						String _value = value.substring(startDelimiterPos + startDelimiter.length(), pairDelimiterPos);
						if (encoding != null) {
							_value = encoding.decode(_value);
						}
						
						List<String> parameterValues = null;
						if (!parameters.containsKey(key)) {
							parameterValues = new ArrayList<String>();
						} else {
							parameterValues = new ArrayList<String>(Arrays.asList(parameters.get(key)));
						}
						parameterValues.add(_value);
						parameters.put(key, parameterValues.toArray(new String[parameterValues.size()]));
					}
					
					if (pairDelimiterPos == -1) break;
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
