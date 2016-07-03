/**
 * 
 */
package za.co.sindi.oauth.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.RequestPath;
import za.co.sindi.oauth.server.config.ServerConfig;
import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.context.OAuthContextFactory;
import za.co.sindi.oauth.server.exception.ServiceException;
import za.co.sindi.oauth.server.factory.FactoryRegistry;
import za.co.sindi.oauth.server.service.ServiceProcessor;
import za.co.sindi.oauth.server.service.impl.DefaultServiceProcessor;
import za.co.sindi.oauth.web.config.ServletServerConfig;

/**
 * @author Bienfait Sindi
 * @since 16 February 2016
 *
 */
public class DefaultServletDispatcher extends AbstractServletDispatcher implements ServletDispatcher {

	public static final String SERVICE_PROCESSOR_KEY = "za.co.sindi.oauth.processor.SERVICE_PROCESSOR_KEY";
	
	private ServerConfig serverConfig;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.servlet.AbstractServletDispatcher#init()
	 */
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();
		serverConfig = new ServletServerConfig(getServletConfig());
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.servlet.AbstractServletDispatcher#doDestroy()
	 */
	@Override
	protected void doDestroy() {
		// TODO Auto-generated method stub
		super.doDestroy();
		getServletContext().removeAttribute(SERVICE_PROCESSOR_KEY);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.web.servlet.ServletDispatcher#execute(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public void execute(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OAuthContextFactory oauthContextFactory = (OAuthContextFactory) FactoryRegistry.getInstance().findFactory(FactoryRegistry.OAUTH_CONTEXT_FACTORY);
		PreConditions.checkSate(oauthContextFactory != null, "OAuthContextFactory is not defined.");
		OAuthContext oauthContext = oauthContextFactory.createOAuthContext(request, response);
		RequestPath requestPath = createRequestPath(request);
		PreConditions.checkState(requestPath != null, "Could not determine request path from scheme '" + request.getProtocol() + "'.");

		final ServiceProcessor serviceProcessor = getServiceProcessor();
		
		try {
			serviceProcessor.service(requestPath, oauthContext);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			throw new ServletException(/*"Error processing service.",*/ e);
		}
	}
	
	protected RequestPath createRequestPath(ServletRequest request) {
		if (request instanceof HttpServletRequest) {
			return new HttpServletRequestPath((HttpServletRequest) request);
		}
		
		return null;
	}
	
	protected ServiceProcessor getServiceProcessor() {
		ServiceProcessor processor = (ServiceProcessor) getServletContext().getAttribute(SERVICE_PROCESSOR_KEY);
		if (processor == null) {
			processor = new DefaultServiceProcessor();
			processor.init(serverConfig);
			
			getServletContext().setAttribute(SERVICE_PROCESSOR_KEY, processor);
		}
		
		return processor;
	}
}
