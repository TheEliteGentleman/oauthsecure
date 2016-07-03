/**
 * 
 */
package za.co.sindi.oauth.client.transport.factory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import za.co.sindi.oauth.client.transport.exception.TransportFactoryException;
import za.co.sindi.oauth.client.transport.http.HttpRequest;
import za.co.sindi.oauth.client.transport.http.HttpResponse;
import za.co.sindi.oauth.client.transport.http.HttpTransport;
import za.co.sindi.oauth.client.transport.http.impl.DefaultHttpTransport;
import za.co.sindi.oauth.client.transport.http.request.DeleteHttpRequest;
import za.co.sindi.oauth.client.transport.http.request.GetHttpRequest;
import za.co.sindi.oauth.client.transport.http.request.PostHttpRequest;
import za.co.sindi.oauth.client.transport.http.request.PutHttpRequest;
import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * @author Buhake Sindi
 * @since 23 February 2012
 *
 */
public class HttpTransportFactory extends AbstractTransportFactory<HttpRequest, HttpResponse> {

	private static final String DEFAULT_HTTP_TRANSPORT_NAME = HttpTransportFactory.class.getName() + ".DEFAULT";
	private static final List<String> VALID_HTTP_METHODS;
	
	static {
		List<String> validHttpMethods = new ArrayList<String>();
		validHttpMethods.add(HttpConstants.METHOD_DELETE);
		validHttpMethods.add(HttpConstants.METHOD_GET);
		validHttpMethods.add(HttpConstants.METHOD_HEAD);
		validHttpMethods.add(HttpConstants.METHOD_POST);
		validHttpMethods.add(HttpConstants.METHOD_PUT);
		validHttpMethods.add(HttpConstants.METHOD_TRACE);
		
		VALID_HTTP_METHODS = Collections.unmodifiableList(validHttpMethods);
	}
	
	//Funny way of doing construct
	{
		registerTransport(DEFAULT_HTTP_TRANSPORT_NAME, DefaultHttpTransport.class);
	}
	
	/* (non-Javadoc)
	 * @see net.oauth.transport.factory.TransportFactory#createTransport()
	 */
	@Override
	public HttpTransport createTransport() throws TransportFactoryException {
		// TODO Auto-generated method stub
		return (HttpTransport) createTransport(DEFAULT_HTTP_TRANSPORT_NAME);
	}
	
	/* (non-Javadoc)
	 * @see net.oauth.transport.factory.TransportFactory#createRequest(java.lang.String, java.lang.String)
	 */
	@Override
	public HttpRequest createRequest(String method, String path) throws TransportFactoryException {
		// TODO Auto-generated method stub
		if (method == null || method.isEmpty()) {
			throw new IllegalArgumentException("No method provided.");
		}
		
		if (path == null || path.isEmpty()) {
			throw new IllegalArgumentException("No path provided.");
		}
		
		if (!VALID_HTTP_METHODS.contains(method)) {
			throw new TransportFactoryException("Invalid HTTP method: " + method);
		}
		
		HttpRequest request = null;
		try {
			URI uri = new URI(path);
			
			if (HttpConstants.METHOD_DELETE.equals(method)) {
				request = new DeleteHttpRequest(uri);
			} else if (HttpConstants.METHOD_GET.equals(method)) {
				request = new GetHttpRequest(uri);
			} else if (HttpConstants.METHOD_POST.equals(method)) {
				request = new PostHttpRequest(uri);
			} else if (HttpConstants.METHOD_PUT.equals(method)) {
				request = new PutHttpRequest(uri);
			}
			
//			if (parameters != null && !parameters.isEmpty()) {
//				if (request instanceof UriQueryParameterHttpRequest) {
//					((UriQueryParameterHttpRequest)request).setHttpParameters(parameters);
//				} else if (request instanceof EntityBasedHttpRequest) {
//					((EntityBasedHttpRequest)request).setEntity(new HttpParameterEntity(parameters, "UTF-8"));
//				}
//			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new TransportFactoryException(e);
		}
		
		return request;
	}
}
