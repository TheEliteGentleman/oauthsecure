/**
 * 
 */
package za.co.sindi.oauth.server.request;

import java.util.logging.Logger;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.context.RequestContext;

/**
 * @author Bienfait Sindi
 * @since 16 February 2016
 *
 */
public abstract class AbstractOAuthRequest implements OAuthRequest {
	
	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.request.OAuthRequest#execute(za.co.sindi.oauth.server.context.RequestContext)
	 */
	@Override
	public void execute(RequestContext request) {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(request != null, "RequestContext is null.");
		PreConditions.checkArgument(request.isSecure(), "Incoming request channel is unsecure (protocol = " + request.getScheme() + ").");
		
		doExecute(request);
	}

	protected abstract void doExecute(RequestContext request);
}
