/**
 * 
 */
package za.co.sindi.oauth.server.service.impl;

import java.lang.reflect.Method;

import za.co.sindi.common.utils.Annotations;
import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.annotation.Request;
import za.co.sindi.oauth.server.config.element.RequestConfig;
import za.co.sindi.oauth.server.config.element.ServiceConfig;
import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.context.RequestContext;
import za.co.sindi.oauth.server.context.ResponseContext;
import za.co.sindi.oauth.server.exception.ServiceException;
import za.co.sindi.oauth.server.object.BeanFactory;
import za.co.sindi.oauth.server.result.Result;
import za.co.sindi.oauth.server.service.Service;
import za.co.sindi.oauth.server.service.ServiceInvocation;

/**
 * @author Bienfait Sindi
 * @since 04 January 2015
 *
 */
public class DefaultServiceInvocation implements ServiceInvocation {

	private static final Class<?>[] PARAMETER_TYPES = new Class<?>[] {RequestContext.class, ResponseContext.class};
	
	private BeanFactory beanFactory;
	private ServiceConfig serviceConfig;
	private RequestConfig requestConfig;
	private Object bean;

	/**
	 * @param bean
	 * @param beanFactory
	 * @param serviceConfig
	 * @param requestConfig
	 */
	public DefaultServiceInvocation(Object bean, BeanFactory beanFactory, ServiceConfig serviceConfig, RequestConfig requestConfig) {
		super();
		PreConditions.checkArgument(bean != null, "A bean cannot be null.");
		PreConditions.checkArgument(beanFactory != null, "A BeanFactory cannot be null.");
		PreConditions.checkArgument(serviceConfig != null, "A ServiceConfig cannot be null.");
		PreConditions.checkArgument(requestConfig != null, "A RequestConfig cannot be null.");
		this.bean = bean;
		this.beanFactory = beanFactory;
		this.serviceConfig = serviceConfig;
		this.requestConfig = requestConfig;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.service.ServiceInvocation#getServiceConfig()
	 */
	@Override
	public ServiceConfig getServiceConfig() {
		// TODO Auto-generated method stub
		return serviceConfig;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.service.ServiceInvocation#invoke(za.co.sindi.oauth.server.context.OAuthContext)
	 */
	@Override
	public Result invoke(OAuthContext context) throws Exception {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(bean != null, "A OAuthContext cannot be null.");
		
		RequestContext request = context.getContainer().getRequest();
		ResponseContext response = context.getContainer().getResponse();
		
		try {
			if (bean instanceof Service) {
				return ((Service)bean).service(request, response);
			}
			
			Method method = bean.getClass().getMethod(requestConfig.getOperation(), PARAMETER_TYPES);
			PreConditions.checkState(method != null, "No method found with name '" + requestConfig.getOperation() + "'.");
			Request requestAnnotation = Annotations.findAnnotation(method, Request.class);
			if (requestAnnotation == null) {
				throw new ServiceException("Method '" + requestConfig.getOperation() + "' contains no @Request annotation.");
			}
			
			if (requestAnnotation.method() == null || requestAnnotation.method().length == 0) {
				throw new ServiceException("Method '" + requestConfig.getOperation() + "' @Request annotation 'method' attribute has not been set.");
			}
			
			boolean requestFound = false;
			for (String requestMethod : requestAnnotation.method()) {
				if (requestConfig.getMethod().equals(requestMethod)) {
					requestFound = true;
					break ;
				}
			}
			
			if (!requestFound) {
				throw new ServiceException("No " + request.getScheme().toUpperCase() + " request method '" + requestConfig.getMethod() + "' has been set on @Request annotation 'method' attribute (method=" + method.toString() + ").");
			}
			
			boolean isAccessible = method.isAccessible();
			try {
				method.setAccessible(true);
				Object result = method.invoke(bean, new Object[]{request, response});
				if (result != null) {
					if (!(result instanceof Result)) {
						throw new ServiceException("Result returned from " + method.toString() + " is of type " + result.getClass().getName() + " instead of type " + Result.class.getName());
					}
					
					return (Result) result;
				}
			} finally {
				method.setAccessible(isAccessible);
			}
		} finally {
			beanFactory.destroyBean(bean);
		}
			
		return null;
	}
}
