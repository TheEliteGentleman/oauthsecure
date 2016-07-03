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
 * @since 18 January 2016
 *
 */
public class HttpHeaderParameterCodec extends HttpParameterCodec {

	/**
	 * @param encoding
	 */
	public HttpHeaderParameterCodec() {
		super("=\"", "\"", ",");
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param encoding
	 */
	public HttpHeaderParameterCodec(StringCodec encoding) {
		super("=\"", "\"", ",", encoding);
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
				if (sb.length() > 0) {
					sb.append(pairDelimiter);
				}
				
				String key = entry.getKey();
				StringBuilder values = new StringBuilder();
				for (String value : entry.getValue()) {
					if (values.length() > 0) {
						values.append(",");
					}
					
					values.append(encoding != null ? encoding.encode(value) : value);
				}
				
				sb.append(encoding != null ? encoding.encode(key) : key).append(startDelimiter).append(values).append(endDelimiter);
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
					int startDelimiterPos = value.indexOf(startDelimiter, keyStartPos + 1);
					String key = (startDelimiterPos < keyStartPos) ? value.substring(keyStartPos) : value.substring(keyStartPos, startDelimiterPos);
					if (startDelimiterPos < keyStartPos) {
						if (!parameters.containsKey(key)) {
							parameters.put(key, EMTPY_STRING_ARRAY);
						}
						
						break;
					}
					
					int startValuePos = startDelimiterPos + startDelimiter.length();
					int endDelimiterPos = value.indexOf(endDelimiter, startValuePos);
					if (endDelimiterPos < startDelimiterPos) break;
//					String key = value.substring(keyStartPos, startDelimiterPos);
					String _value = value.substring(startValuePos, endDelimiterPos);
					String[] values = _value.split(",");
					
					List<String> parameterValues = null;
					if (!parameters.containsKey(key)) {
						parameterValues = new ArrayList<String>();
					} else {
						parameterValues = new ArrayList<String>(Arrays.asList(parameters.get(key)));
					}
					
					for (String v : values) {
						if (encoding != null) {
							v = encoding.decode(v);
						}
						
						parameterValues.add(v);
					}
					
					parameters.put(key, parameterValues.toArray(new String[parameterValues.size()]));
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
