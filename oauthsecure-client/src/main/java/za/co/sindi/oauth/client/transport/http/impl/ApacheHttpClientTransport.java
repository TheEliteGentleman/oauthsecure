/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.impl;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.impl.client.CloseableHttpClient;

import za.co.sindi.oauth.client.transport.exception.TransportException;
import za.co.sindi.oauth.client.transport.http.Header;
import za.co.sindi.oauth.client.transport.http.HttpRequest;
import za.co.sindi.oauth.client.transport.http.HttpResponse;
import za.co.sindi.oauth.client.transport.http.HttpTransport;
import za.co.sindi.oauth.client.transport.http.header.BasicHeader;
import za.co.sindi.oauth.client.transport.http.response.BasicHttpResponse;
import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * @author Buhake Sindi
 * @since 10 February 2012
 *
 */
public class ApacheHttpClientTransport implements HttpTransport {
	
//	private static final Logger logger = Logger.getLogger(ApacheHttpClientTransport.class);
	private HttpClient httpClient;

	/**
	 * @param httpClient
	 */
	public ApacheHttpClientTransport(HttpClient httpClient) {
		super();
		this.httpClient = httpClient;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.Transport#send(net.oauth.transport.Request)
	 */
	@Override
	public HttpResponse send(HttpRequest request) throws TransportException {
		// TODO Auto-generated method stub
		if (httpClient == null) {
			throw new TransportException("Apache HttpClient is null.");
		}
		
		if (request == null) {
			throw new IllegalArgumentException("HttpRequest is required.");
		}
		
		HttpResponse response = null;
		try {
			org.apache.http.HttpResponse httpResponse = httpClient.execute(createApacheHttpRequest(request));
			response = createHttpResponse(httpResponse);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e);
		}
		
		return response;
	}

	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if (httpClient != null) {
			if (httpClient instanceof CloseableHttpClient) { //It is a Closeable ....
				((CloseableHttpClient)httpClient).close();
			}
			
//			httpClient.getConnectionManager().shutdown();
		}
	}
	
	private HttpRequestBase createApacheHttpRequest(HttpRequest request) {
		HttpRequestBase requestBase = null;
		
		if (request != null) {
			String method = request.getMethod();
			if (HttpConstants.METHOD_DELETE.equals(method)) {
				requestBase = new HttpDelete();
			} else if (HttpConstants.METHOD_GET.equals(method)) {
				requestBase = new HttpGet();
			} else if (HttpConstants.METHOD_HEAD.equals(method)) {
				requestBase = new HttpHead();
			} else if (HttpConstants.METHOD_POST.equals(method)) {
				requestBase = new HttpPost();
			} if (HttpConstants.METHOD_PUT.equals(method)) {
				requestBase = new HttpPut();
			} else if (HttpConstants.METHOD_DELETE.equals(method)) {
				requestBase = new HttpTrace();
			}
			
			requestBase.setURI(request.getURI());
			
			if (request.getHeaders() != null) {
				for (Header header : request.getHeaders()) {
					requestBase.setHeader(header.getName(), header.getValue());
				}
			}
		}
		
		return requestBase;
	}
	
	private HttpResponse createHttpResponse(org.apache.http.HttpResponse apacheHttpResponse) throws IllegalStateException, IOException {
		HttpResponse response = null;
		
		if (apacheHttpResponse != null) {
			ProtocolVersion version = apacheHttpResponse.getProtocolVersion();
			StatusLine statusLine = apacheHttpResponse.getStatusLine();
			response = new BasicHttpResponse(version.getMajor(), version.getMinor(), statusLine.getStatusCode(), statusLine.getReasonPhrase(), new BufferedInputStream(apacheHttpResponse.getEntity().getContent()));
			
			if (apacheHttpResponse.getAllHeaders() != null) {
				for (org.apache.http.Header header : apacheHttpResponse.getAllHeaders()) {
					response.addHeader(new BasicHeader(header.getName(), header.getValue()));
				}
			}
		}
		
		return response;
	}
}
