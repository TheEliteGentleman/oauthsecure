/**
 * 
 */
package za.co.sindi.oauth.server.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.RequestPath;
import za.co.sindi.oauth.server.config.element.OAuthConfig;
import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.exception.ResultException;
import za.co.sindi.oauth.server.exception.ServiceException;
import za.co.sindi.oauth.server.factory.FactoryRegistry;
import za.co.sindi.oauth.server.result.Result;
import za.co.sindi.oauth.server.service.AbstractServiceProcessor;
import za.co.sindi.oauth.server.service.ServiceInvocation;
import za.co.sindi.oauth.server.service.ServiceInvocationFactory;
import za.co.sindi.oauth.server.service.ServiceProcessor;

/**
 * @author Bienfait Sindi
 * @since 18 November 2014
 *
 */
public class DefaultServiceProcessor extends AbstractServiceProcessor implements ServiceProcessor {

	private static final Logger LOGGER = Logger.getLogger(DefaultServiceProcessor.class.getName());
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.service.AbstractServiceProcessor#service(za.co.sindi.oauth.server.RequestPath)
	 */
	@Override
	protected void service(RequestPath requestPath) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			OAuthContext context = getOauthContext();
			OAuthConfig oauthConfig = context.getOAuthConfig();
			ServiceInvocationFactory serviceInvocationFactory = (ServiceInvocationFactory) FactoryRegistry.getInstance().findFactory(FactoryRegistry.SERVICE_INVOCATION_FACTORY);
			PreConditions.checkSate(serviceInvocationFactory != null, "No Service Invocation factory was found.");
			ServiceInvocation serviceInvocation = serviceInvocationFactory.createServiceInvocation(oauthConfig, requestPath.getPath());
			Result result = serviceInvocation.invoke(context);
			if (result != null) {
				try {
					result.executeResult(context);
				} catch (ResultException e) {
					// TODO Auto-generated catch block
					//Handle Exception....
					LOGGER.log(Level.SEVERE, "Unable to execute Result.", e);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (e instanceof ServiceException) {
				throw ((ServiceException)e);
			}
			
			throw new ServiceException("Unable to execute service.", e);
		}
	}
}
