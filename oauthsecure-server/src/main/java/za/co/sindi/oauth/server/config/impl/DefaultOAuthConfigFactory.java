/**
 * 
 */
package za.co.sindi.oauth.server.config.impl;

import za.co.sindi.oauth.server.config.AbstractOAuthConfigFactory;

/**
 * @author Bienfait Sindi
 * @since 25 October 2014
 *
 */
public class DefaultOAuthConfigFactory extends AbstractOAuthConfigFactory {

	/**
	 * 
	 */
	public DefaultOAuthConfigFactory() {
		super();
		// TODO Auto-generated constructor stub
		//Add Parser
		setOAuthConfigParser(new DefaultOAuthConfigParser());
	}
}
