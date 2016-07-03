/**
 * 
 */
package za.co.sindi.oauth.server;

import java.util.Map;

/**
 * @author Bienfait Sindi
 * @since 16 February 2016
 *
 */
public interface Path {

	public String getAbsolutePath();
	public String getPath();
	public Map<String, String[]> gethPathParameters();
}
