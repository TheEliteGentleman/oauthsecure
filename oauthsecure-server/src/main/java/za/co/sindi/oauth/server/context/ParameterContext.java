/**
 * 
 */
package za.co.sindi.oauth.server.context;

import java.util.Map;

/**
 * @author Buhake Sindi
 * @since 14 April 2012
 *
 */
public interface ParameterContext<V> {
	
	public String getParameter(String name);
	public String[] getParameterNames();
	public Map<String, V> getParameterMap();
	public V getParameterValue(String name);
}
