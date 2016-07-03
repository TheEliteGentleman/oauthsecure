/**
 * 
 */
package za.co.sindi.oauth.webapp;

import java.util.logging.Logger;

import za.co.sindi.oauth.server.config.OAuthConfigFactory;
import za.co.sindi.oauth.server.config.impl.DefaultOAuthConfigFactory;

/**
 * @author Bienfait Sindi
 * @since 14 November 2014
 *
 */
public abstract class AbstractOAuthInitializer implements OAuthInitializer {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private OAuthConfigFactory configFactory;

	/**
	 * @return the configFactory
	 */
	public OAuthConfigFactory getConfigFactory() {
		if (configFactory == null) {
			configFactory = new DefaultOAuthConfigFactory();
		}
		
		return configFactory;
	}

	/**
	 * @param configFactory the configFactory to set
	 */
	public void setConfigFactory(OAuthConfigFactory configFactory) {
		this.configFactory = configFactory;
	}
}
