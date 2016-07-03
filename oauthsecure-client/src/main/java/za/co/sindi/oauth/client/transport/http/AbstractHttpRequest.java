/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.net.URI;

import org.apache.log4j.Logger;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public abstract class AbstractHttpRequest extends AbstractMessageHeader implements HttpRequest {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	private String method;
	private URI uri;

	/**
	 * @param method
	 * @param uri
	 */
	protected AbstractHttpRequest(String method, URI uri) {
		super();
		this.method = method;
		this.uri = uri;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.Request#getMethod()
	 */
	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return method;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpRequest#getURI()
	 */
	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		return uri;
	}
}
