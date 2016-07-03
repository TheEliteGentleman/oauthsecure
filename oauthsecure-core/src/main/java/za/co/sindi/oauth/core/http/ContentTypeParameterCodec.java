/**
 * 
 */
package za.co.sindi.oauth.core.http;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import za.co.sindi.oauth.core.util.ParameterDecoder;
import za.co.sindi.oauth.core.util.ParameterEncoder;

/**
 * @author Bienfait Sindi
 * @since 18 February 2016
 *
 */
public class ContentTypeParameterCodec implements ParameterEncoder<String>, ParameterDecoder<String> {

	private static final char PAIR_DELIMITER = ';';
	private static final char DOUBLE_QUOTE = '"';
	private static Collection<Character> SPECIAL_CHARACTERS = Collections.unmodifiableList(Arrays.asList('(', ')', '<', '>', '@'
																										, ',', ';', ':', '\\', '"'
																										,'/', '[', ']', '?', '.', '='));
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.core.util.ParameterEncoder#encode(java.util.Map)
	 */
	@Override
	public String encode(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		if (parameters == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> entry : parameters.entrySet()) {
			if (sb.length() > 0) {
				sb.append(PAIR_DELIMITER);
			}
			
			String value = entry.getValue();
			boolean needsQuoting = containsSpecialCharacters(value);
			sb.append(entry.getKey()).append("=");
			if (needsQuoting) {
				sb.append(DOUBLE_QUOTE);
			}
			sb.append(value);
			if (needsQuoting) {
				sb.append(DOUBLE_QUOTE);
			}
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
		
		Map<String, String> parameterMap = new LinkedHashMap<>();
		boolean quoted = false;
		String parameter = "";
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == DOUBLE_QUOTE) {
				quoted = !quoted;
				continue;
			} else if (c == PAIR_DELIMITER) {
				if (quoted) {
					parameter += c;
					continue;
				} else {
					decode(parameter, parameterMap);
					parameter = "";
				}
			} else {
				parameter += c;
			}
		}
		
		if (parameter != null && !parameter.isEmpty()) {
			decode(parameter, parameterMap);
		}
		
		return Collections.unmodifiableMap(parameterMap);
	}
	
	private boolean containsSpecialCharacters(String s) {
		char[] c = s.toCharArray();
		for (Character sc : SPECIAL_CHARACTERS) {
			if (sc.equals(c)) {
				return true;
			}
		}
		
		return false;
	}
	
	private void decode(String s, Map<String, String> parameterMap) {
		int parameterPos = s.indexOf('=');
		if (parameterPos > -1) {
			parameterMap.put(s.substring(0, parameterPos), s.substring(parameterPos + 1));
		} else {
			parameterMap.put(s, "");
		}
	}
}
