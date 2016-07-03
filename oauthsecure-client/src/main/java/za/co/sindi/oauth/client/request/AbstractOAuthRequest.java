/**
 * 
 */
package za.co.sindi.oauth.client.request;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import za.co.sindi.oauth.client.OAuthRequest;
import za.co.sindi.oauth.client.ResponseHandler;
import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.transport.Interceptor;
import za.co.sindi.oauth.client.transport.Request;
import za.co.sindi.oauth.client.transport.Response;
import za.co.sindi.oauth.client.transport.Transport;
import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.exception.TransportException;
import za.co.sindi.oauth.client.transport.exception.TransportFactoryException;
import za.co.sindi.oauth.client.transport.factory.TransportFactory;
import za.co.sindi.oauth.client.transport.interceptor.InterceptorContext;

/**
 * @author Buhake Sindi
 * @since 15 February 2012
 *
 */
public abstract class AbstractOAuthRequest implements OAuthRequest, InterceptorContext<Request> {

	protected final Logger logger = Logger.getLogger(this.getClass());
	private String requestMethod;
	private String requestUrl;
	private TransportFactory<? extends Request, ? extends Response> transportFactory;
	private ResponseHandler<?> responseHandler;
	private String transportId;
	
	//Interceptor
	private List<Interceptor<Request>> interceptors = new ArrayList<Interceptor<Request>>();
	
	/**
	 * @param requestMethod
	 * @param requestUrl
	 */
	protected AbstractOAuthRequest(String requestMethod, String requestUrl) {
		this(requestMethod, requestUrl, null);
	}

	/**
	 * 
	 * @param requestMethod
	 * @param requestUrl
	 * @param responseHandler
	 */
	protected AbstractOAuthRequest(String requestMethod, String requestUrl, ResponseHandler<?> responseHandler) {
		super();
		
		if (requestMethod == null || requestMethod.isEmpty()) {
			throw new IllegalArgumentException("Request method is null or empty.");
		}
		
		if (requestUrl == null || requestUrl.isEmpty()) {
			throw new IllegalArgumentException("Request URL is null or empty.");
		}
		
		this.requestMethod = requestMethod;
		this.requestUrl = requestUrl;
		this.responseHandler = responseHandler;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.interceptor.InterceptorContext#addInterceptor(net.oauth.transport.Interceptor)
	 */
	@Override
	public void addInterceptor(Interceptor<Request> interceptor) {
		// TODO Auto-generated method stub
		if (interceptor != null) {
			interceptors.add(interceptor);
		}
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.interceptor.InterceptorContext#removeInterceptor(net.oauth.transport.Interceptor)
	 */
	@Override
	public void removeInterceptor(Interceptor<Request> interceptor) {
		// TODO Auto-generated method stub
		if (interceptor != null) {
			interceptors.remove(interceptor);
		}
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.interceptor.InterceptorContext#clearInterceptors()
	 */
	@Override
	public void clearInterceptors() {
		// TODO Auto-generated method stub
		interceptors.clear();
	}

	/**
	 * @param transportId the transportId to set
	 */
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthRequest#getRequestMethod()
	 */
	@Override
	public String getRequestMethod() {
		// TODO Auto-generated method stub
		return requestMethod;
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthRequest#getRequestUrl()
	 */
	@Override
	public String getRequestUrl() {
		// TODO Auto-generated method stub
		return requestUrl;
	}

	/**
	 * @return the responseHandler
	 */
	public ResponseHandler<?> getResponseHandler() {
		return responseHandler;
	}

	/**
	 * @return the transportFactory
	 */
	protected TransportFactory<? extends Request, ? extends Response> getTransportFactory() {
		return transportFactory;
	}

	/**
	 * @param transportFactory the transportFactory to set
	 */
	protected void setTransportFactory(TransportFactory<? extends Request, ? extends Response> transportFactory) {
		this.transportFactory = transportFactory;
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthRequest#execute()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T execute() throws OAuthRequestException, OAuthResponseException {
		// TODO Auto-generated method stub
		return (T) execute(responseHandler);
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthRequest#execute(net.oauth.client.ResponseHandler)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T execute(ResponseHandler<T> responseHandler) throws OAuthRequestException, OAuthResponseException {
		// TODO Auto-generated method stub
		if (transportFactory == null) {
			throw new IllegalArgumentException("No Transport Factory provided.");
		}
		
		if (responseHandler == null) {
			throw new IllegalArgumentException("Response handler may not be null.");
		}
		
		Transport<Request, Response> transport = null;
		try {
			if (transportId != null && !transportId.isEmpty()) {
				transport = (Transport<Request, Response>) transportFactory.createTransport(transportId);
			} else {
				transport = (Transport<Request, Response>) transportFactory.createTransport();
			}
			
			if (transport == null) {
				throw new OAuthRequestException("No transport created.");
			}
			
			//Getting request
			Request request = transportFactory.createRequest(requestMethod, requestUrl);
			
			//Authenticate
			authenticateRequest(request);
			
			//Now, get intercepted
			if (interceptors != null && !interceptors.isEmpty()) {
				if (logger.isInfoEnabled()) {
					logger.info("Sending request (of type " + request.getClass().getName() + ") to " + interceptors.size() + " interceptor(s).");
				}
				
				for (Interceptor<Request> interceptor : interceptors) {
					interceptor.accept(request);
				}
			}
			
			//Get response
			return responseHandler.handleResponse(transport.send(request));
		} catch (TransportFactoryException e) {
			// TODO Auto-generated catch block
			throw new OAuthRequestException(e);
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			throw new OAuthRequestException(e);
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			throw new OAuthRequestException(e);
		} finally {
			if (transport != null) {
				if (logger.isInfoEnabled()) {
					logger.info("Closing transport: " + transport.getClass().getName());
				}
				
				try {
					if (transport instanceof Closeable) {
						((Closeable) transport).close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("Error closing transport.", e);
				}
			}
		}
	}
	
	protected abstract void authenticateRequest(Request request) throws OAuthRequestException, AuthorizationException;
}
