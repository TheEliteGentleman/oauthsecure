/**
 * 
 */
package za.co.sindi.oauth.server.scanner;

import java.util.Collection;

import za.co.sindi.oauth.server.exception.ScanningException;
import za.co.sindi.oauth.server.resource.Resource;

/**
 * @author Bienfait Sindi
 * @since 27 October 2014
 *
 */
public interface ResourceScanner {

	public Collection<Resource> scan() throws ScanningException;
	public ClassLoader getClassLoader();
}
