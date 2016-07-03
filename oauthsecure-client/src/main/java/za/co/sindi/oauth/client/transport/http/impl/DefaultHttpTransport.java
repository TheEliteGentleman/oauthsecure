/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.log4j.Logger;

import za.co.sindi.oauth.client.transport.exception.TransportException;
import za.co.sindi.oauth.client.transport.http.EntityBasedHttpRequest;
import za.co.sindi.oauth.client.transport.http.Header;
import za.co.sindi.oauth.client.transport.http.HttpEntity;
import za.co.sindi.oauth.client.transport.http.HttpRequest;
import za.co.sindi.oauth.client.transport.http.HttpResponse;
import za.co.sindi.oauth.client.transport.http.HttpTransport;
import za.co.sindi.oauth.client.transport.http.header.BasicHeader;
import za.co.sindi.oauth.client.transport.http.response.BasicHttpResponse;

/**
 * @author Buhake Sindi
 * @since 26 January 2012
 *
 */
public class DefaultHttpTransport implements HttpTransport {

	private static final Logger logger = Logger.getLogger(DefaultHttpTransport.class);
	private HttpURLConnection connection = null;
	
	/**
	 * 
	 */
	public DefaultHttpTransport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.AbstractTransport#sendRequest(net.oauth.transport.Request)
	 */
	@Override
	public HttpResponse send(HttpRequest request) throws TransportException {
		// TODO Auto-generated method stub
		if (request == null) {
			throw new TransportException("Cannot transport a null request.");
		}
		
		if (request.getURI() == null) {
			throw new TransportException("No request URI provided.");
		}
		
		if (logger.isInfoEnabled()) {
			logger.info(request.getMethod() + " " + request.getURI().toString() + " HTTP/1.1");
		}
		
		HttpResponse response = null;
		HttpEntity entity = (request instanceof EntityBasedHttpRequest) ? ((EntityBasedHttpRequest)request).getEntity() : null;
		String scheme = request.getURI().getScheme();
		if (!"http".equals(scheme) && !"https".equals(scheme)) {
			throw new TransportException("Invalid scheme " + scheme + ". Required: https.");
		}
		
		if (entity != null) {
			request.addHeader(new BasicHeader("Content-Type", entity.getContentType() + ";charset=" + entity.getCharset()));
			request.addHeader(new BasicHeader("Content-Length", entity.getContentLength()));
		}
		
		try {
			connection = (HttpURLConnection) request.getURI().toURL().openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(request.getMethod());
			
			//Add request headers
			for (Header requestHeader : request.getHeaders()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Header [" + requestHeader.getName() + ": " + requestHeader.getValue() + "]");
				}
				
				connection.addRequestProperty(requestHeader.getName(), requestHeader.getValue());
			}
			
			if (entity != null) {
				connection.getOutputStream().write(entity.getContent());
				connection.getOutputStream().flush();
			}
			
			//Execute.
			InputStream is = null;
			try {
				is = connection.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if (logger.isInfoEnabled()) {
					logger.info("Received IOException during HttpURLConnection.getInputStream(). Getting HttpURLConnection.getErrorStream().");
				}
			}
			
			//Getting all relevant stream
			if (is == null && connection.getErrorStream() != null) {
				is = connection.getErrorStream();
			}
			
			int statusCode = connection.getResponseCode();
			String reasonPhrase = connection.getResponseMessage();
			
			//Status Line
			String statusLine = connection.getHeaderField(0);
			if (!statusLine.startsWith("HTTP/")) {
				throw new TransportException("Invalid status line '" + statusLine + "'.");
			}
			
			int forwardSlashPos = statusLine.indexOf('/', 4);
			int dotPos = statusLine.indexOf('.', forwardSlashPos + 1);
			int majorVersion = Integer.parseInt(statusLine.substring(forwardSlashPos + 1, dotPos));
			int minorVersion = Integer.parseInt(statusLine.substring(dotPos + 1, statusLine.indexOf(" ", dotPos + 1)));
			
			response = new BasicHttpResponse(majorVersion, minorVersion, statusCode, reasonPhrase, new BufferedInputStream(is));
			
			//Populate response headers.
			for (String key : connection.getHeaderFields().keySet()) {
				if (null != key) {//The first header field is the status line, so ignore it.
					response.addHeader(new BasicHeader(key, connection.getHeaderField(key)));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new TransportException(e);
		} finally {
			if (logger.isInfoEnabled()) {
				logger.warn("Make sure you call the close() method to close all relevant resources.");
			}
		}
		
		return response;
	}


	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if (connection != null) {
			connection.disconnect();
			connection = null;
		}
	}
}
