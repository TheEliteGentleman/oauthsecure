/**
 * 
 */
package za.co.sindi.oauth.server.service;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.RequestPath;
import za.co.sindi.oauth.server.config.ServerConfig;
import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.exception.ServiceException;

/**
 * @author Bienfait Sindi
 * @since 18 November 2014
 *
 */
public abstract class AbstractServiceProcessor implements ServiceProcessor {

	private ServerConfig serverConfig;
	private OAuthContext oauthContext;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.service.ServiceProcessor#init(za.co.sindi.oauth.server.config.ServerConfig)
	 */
	@Override
	public void init(ServerConfig serverConfig) {
		// TODO Auto-generated method stub
		this.serverConfig = serverConfig;
		init();
	}

	/**
	 * @return the serverConfig
	 */
	protected ServerConfig getServerConfig() {
		return serverConfig;
	}
	
	protected void init() {
		//TODO: Nothing....
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.service.ServiceProcessor#service(za.co.sindi.oauth.server.RequestPath, za.co.sindi.oauth.server.context.OAuthContext)
	 */
	@Override
	public void service(RequestPath requestPath, OAuthContext context) throws ServiceException {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(context != null, "OAuthContext is not provided.");
		oauthContext = context;
		service(requestPath);
	}
	
	/**
	 * @return the oauthContext
	 */
	protected OAuthContext getOauthContext() {
		return oauthContext;
	}

	protected abstract void service(RequestPath requestPath) throws ServiceException;
}
