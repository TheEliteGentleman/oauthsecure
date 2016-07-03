/**
 * 
 */
package za.co.sindi.oauth.server.service.impl;

import java.util.Collection;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.Path;
import za.co.sindi.oauth.server.config.element.BeanConfig;
import za.co.sindi.oauth.server.config.element.OAuthConfig;
import za.co.sindi.oauth.server.config.element.RequestConfig;
import za.co.sindi.oauth.server.config.element.ServiceConfig;
import za.co.sindi.oauth.server.exception.ServiceException;
import za.co.sindi.oauth.server.factory.FactoryRegistry;
import za.co.sindi.oauth.server.object.BeanFactory;
import za.co.sindi.oauth.server.service.ServiceInvocation;
import za.co.sindi.oauth.server.service.ServiceInvocationFactory;

/**
 * @author Bienfait Sindi
 * @since 04 January 2016
 *
 */
public class DefaultServiceInvocationFactory implements ServiceInvocationFactory {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.service.ServiceInvocationFactory#createServiceInvocation(za.co.sindi.oauth.server.config.element.OAuthConfig, za.co.sindi.oauth.server.Path)
	 */
	@Override
	public ServiceInvocation createServiceInvocation(OAuthConfig oauthConfig, Path path) throws ServiceException {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(oauthConfig != null, "No OAuthConfig was specified.");
		PreConditions.checkArgument(path != null, "No path was specified.");
		
		String pathInfo = path.getPath();
//		ServiceConfig serviceConfig = oauthConfig.findServiceConfigByAddress(pathInfo);
		ServiceConfig serviceConfig = findServiceConfig(oauthConfig, pathInfo);
		if (serviceConfig == null) {
			throw new ServiceException("No Service defined for path '" + pathInfo + "'.");
		}
		
		BeanConfig beanConfig = oauthConfig.findBeanConfig(serviceConfig.getBeanReferenceId());
		if (beanConfig == null) {
			throw new ServiceException("No Bean defined with ID '" + serviceConfig.getBeanReferenceId() + "'.");
		}
		
		//Get BeanFactory.
		BeanFactory beanFactory = (BeanFactory) FactoryRegistry.getInstance().findFactory(FactoryRegistry.BEAN_FACTORY);
		PreConditions.checkSate(beanFactory != null, "No BeanFactory was found.");
		Object service = beanFactory.getBean(beanConfig.getClassName());
		PreConditions.checkState(service != null, "No Bean found (class=" + beanConfig.getClassName() +").");
		
		return new DefaultServiceInvocation(service, beanFactory, serviceConfig, findRequestConfig(serviceConfig, pathInfo));
	}
	
	protected ServiceConfig findServiceConfig(OAuthConfig oauthConfig, String pathInfo) {
		ServiceConfig serviceConfig = oauthConfig.findServiceConfigByAddress(pathInfo);
		if (serviceConfig == null) {
			for (ServiceConfig config : oauthConfig.getServiceConfigs()) {
				if (findRequestConfig(config, pathInfo) != null) {
					serviceConfig = config;
					break;
				}
			}
		}
		
		return serviceConfig;
	}
	
	protected RequestConfig findRequestConfig(ServiceConfig serviceConfig, String pathInfo) {
		Collection<RequestConfig> requestConfigs = serviceConfig.getRequestConfigs();
		if (requestConfigs != null) {
			for (RequestConfig requestConfig : requestConfigs) {
				String fullPath = serviceConfig.getAddress() + requestConfig.getPath();
				if (pathInfo.equals(fullPath)) {
					return requestConfig;
				}
			}
		}
		
		return null;
	}
}
