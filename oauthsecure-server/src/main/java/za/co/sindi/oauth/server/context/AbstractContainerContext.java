/**
 * 
 */
package za.co.sindi.oauth.server.context;

/**
 * @author Bienfait Sindi
 * @since 23 November 2014
 *
 */
public abstract class AbstractContainerContext implements ContainerContext {

	private ApplicationContext application;
	private RequestContext request;
	private ResponseContext response;

	/**
	 * @param application
	 */
	protected AbstractContainerContext(ApplicationContext application) {
		super();
		this.application = application;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.ContainerContext#getApplication()
	 */
	@Override
	public ApplicationContext getApplication() {
		// TODO Auto-generated method stub
		return application;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.ContainerContext#getRequest()
	 */
	@Override
	public RequestContext getRequest() {
		// TODO Auto-generated method stub
		return request;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.ContainerContext#getResponse()
	 */
	@Override
	public ResponseContext getResponse() {
		// TODO Auto-generated method stub
		return response;
	}

	/**
	 * @param request the request to set
	 */
	protected void setRequest(RequestContext request) {
		this.request = request;
	}

	/**
	 * @param response the response to set
	 */
	protected void setResponse(ResponseContext response) {
		this.response = response;
	}
}
