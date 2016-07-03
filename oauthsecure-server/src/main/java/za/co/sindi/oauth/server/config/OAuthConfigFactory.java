/**
 * 
 */
package za.co.sindi.oauth.server.config;

import za.co.sindi.oauth.server.config.element.OAuthConfig;
import za.co.sindi.oauth.server.exception.ConfigurationException;
import za.co.sindi.oauth.server.scanner.ResourceScanner;

/**
 * @author Bienfait Sindi
 * @since 18 Factory 2014
 *
 */
public interface OAuthConfigFactory {

	public void setOAuthConfigParser(ConfigParser<OAuthConfig> configParser);
	public void addResourceScanner(ResourceScanner resourceScanner);
//	public void setAnnotationScanner(AnnotationScanner annotationScanner);
	public OAuthConfig create() throws ConfigurationException;
}
