/**
 * 
 */
package za.co.sindi.oauth.core.util;

import java.util.Map;

/**
 * @author Bienfait Sindi
 * @since 18 January 2016
 *
 */
public interface ParameterDecoder<V> {

	public Map<String, V> decode(String value);
}
