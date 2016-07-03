/**
 * 
 */
package za.co.sindi.oauth.web.servlet.context;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import za.co.sindi.oauth.server.context.AbstractContainerContext;
import za.co.sindi.oauth.server.context.RequestContext;
import za.co.sindi.oauth.server.context.ResponseContext;

/**
 * @author Bienfait Sindi
 * @since 23 November 2014
 *
 */
public class ServletContainerContext extends AbstractContainerContext {

	protected ServletRequest servletRequest;
	protected ServletResponse servletResponse;
	
	/**
	 * @param servletRequest
	 * @param servletResponse
	 */
	public ServletContainerContext(ServletRequest servletRequest, ServletResponse servletResponse) {
		super(new ServletApplicationContext(servletRequest.getServletContext()));
		this.servletRequest = servletRequest;
		this.servletResponse = servletResponse;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.AbstractContainerContext#getRequest()
	 */
	@Override
	public RequestContext getRequest() {
		// TODO Auto-generated method stub
		if (super.getRequest() == null) {
			setRequest(new ServletRequestContext(servletRequest));
		}
		
		return super.getRequest();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.AbstractContainerContext#getResponse()
	 */
	@Override
	public ResponseContext getResponse() {
		// TODO Auto-generated method stub
		if (super.getResponse() == null) {
			setResponse(new ServletResponseContext(servletResponse));
		}
		
		return super.getResponse();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.ContainerContext#forward(java.lang.String)
	 */
	@Override
	public void forward(String location) throws Exception {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(location);
		if (dispatcher == null) {
			dispatcher = servletRequest.getServletContext().getRequestDispatcher(location);
		}
		
		dispatcher.forward(servletRequest, servletResponse);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.context.ContainerContext#include(java.lang.String)
	 */
	@Override
	public void include(String location) throws Exception {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(location);
		if (dispatcher == null) {
			dispatcher = servletRequest.getServletContext().getRequestDispatcher(location);
		}
		
		dispatcher.include(servletRequest, servletResponse);
	}
}
