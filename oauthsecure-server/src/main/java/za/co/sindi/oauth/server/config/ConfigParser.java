/**
 * 
 */
package za.co.sindi.oauth.server.config;

import java.io.InputStream;
import java.net.URL;

import za.co.sindi.oauth.server.exception.ConfigurationException;
import za.co.sindi.oauth.server.exception.ParseException;

/**
 * @author Bienfait Sindi
 * @since 10 October 2014
 *
 */
public interface ConfigParser<T> {

	public T parse(URL resource) throws ParseException, ConfigurationException;
	public T parse(InputStream input) throws ParseException, ConfigurationException;
}
