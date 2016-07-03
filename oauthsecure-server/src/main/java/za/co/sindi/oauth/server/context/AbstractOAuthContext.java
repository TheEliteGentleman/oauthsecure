/**
 * 
 */
package za.co.sindi.oauth.server.context;

import za.co.sindi.common.utils.PreConditions;


/**
 * @author Bienfait Sindi
 * @since 16 November 2014
 *
 */
public abstract class AbstractOAuthContext implements OAuthContext {

	private ContainerContext container;

	/**
	 * @param container
	 */
	protected AbstractOAuthContext(ContainerContext container) {
		super();
		PreConditions.checkArgument(container != null, "No Container context was provided.");
		this.container = container;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.OAuthContext#getContainer()
	 */
	@Override
	public ContainerContext getContainer() {
		// TODO Auto-generated method stub
		return container;
	}
}
