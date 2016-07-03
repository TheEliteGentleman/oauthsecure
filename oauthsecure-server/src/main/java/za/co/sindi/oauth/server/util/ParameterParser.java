/**
 * 
 */
package za.co.sindi.oauth.server.util;

import java.util.Map;

/**
 * @author Buhake Sindi
 * @since 09 March 2012
 *
 */
public interface ParameterParser<V> {

	public Map<String, V> parse(String paramString);
}
