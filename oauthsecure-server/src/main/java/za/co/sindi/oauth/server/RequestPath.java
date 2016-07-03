/**
 * 
 */
package za.co.sindi.oauth.server;

import java.net.URI;

/**
 * @author Bienfait Sindi
 * @since 16 February 2016
 *
 */
public interface RequestPath {

	public Path getPath();
	public URI absolutePathURI();
	public URI getRequestURI();
}
