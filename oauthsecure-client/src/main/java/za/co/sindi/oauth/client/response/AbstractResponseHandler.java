/**
 * 
 */
package za.co.sindi.oauth.client.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import za.co.sindi.oauth.client.ResponseHandler;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.transport.Interceptor;
import za.co.sindi.oauth.client.transport.Response;
import za.co.sindi.oauth.client.transport.http.HttpResponse;
import za.co.sindi.oauth.client.transport.interceptor.InterceptorContext;

/**
 * @author Buhake Sindi
 * @since 17 February 2012
 *
 */
public abstract class AbstractResponseHandler<T> implements ResponseHandler<T>, InterceptorContext<Response> {

	protected static final String LINE_SEPARATOR = System.getProperty("line.separator");
	protected final Logger logger = Logger.getLogger(this.getClass());
	private List<Interceptor<Response>> interceptors = new ArrayList<Interceptor<Response>>();
	
	/* (non-Javadoc)
	 * @see net.oauth.client.InterceptorContext#addResponseInterceptor(net.oauth.transport.interceptor.ResponseInterceptor)
	 */
	@Override
	public void addInterceptor(Interceptor<Response> interceptor) {
		// TODO Auto-generated method stub
		if (interceptor != null) {
			interceptors.add(interceptor);
		}
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.InterceptorContext#removeInterceptor(net.oauth.transport.interceptor.ResponseInterceptor)
	 */
	@Override
	public void removeInterceptor(Interceptor<Response> interceptor) {
		// TODO Auto-generated method stub
		if (interceptor != null) {
			interceptors.remove(interceptor);
		}
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.InterceptorContext#clearInterceptor()
	 */
	@Override
	public void clearInterceptors() {
		// TODO Auto-generated method stub
		interceptors.clear();
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthResponseHandler#handleResponse(net.oauth.transport.Response)
	 */
	@Override
	public T handleResponse(Response response) throws OAuthResponseException {
		// TODO Auto-generated method stub
		if (response == null) {
			throw new OAuthResponseException("Invalid Http response received.");
		}
		
		if (interceptors != null && !interceptors.isEmpty()) {
			if (logger.isInfoEnabled()) {
				logger.info("Sending response (of type " + response.getClass().getName() + ") to " + interceptors.size() + " interceptor(s).");
			}
			
			for (Interceptor<Response> interceptor : interceptors) {
				interceptor.accept(response);
			}
		}
		
		if (response instanceof HttpResponse) {
			return handleHttpResponse((HttpResponse) response);
		}
		
		throw new OAuthResponseException("Invalid response of type " + response.getClass().getName());
	}
	
	/**
	 * This method should be overridden if the user wants to handle {@link HttpResponse}.
	 * @param response an HTTP response.
	 * @return  
	 * @throws OAuthResponseException
	 */
	protected abstract T handleHttpResponse(HttpResponse response) throws OAuthResponseException;
}
