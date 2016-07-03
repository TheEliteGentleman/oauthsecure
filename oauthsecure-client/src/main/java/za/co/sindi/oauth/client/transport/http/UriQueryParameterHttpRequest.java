/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.net.URI;
import java.net.URISyntaxException;

import za.co.sindi.oauth.core.encoding.WWWFormURLEncodingCodec;
import za.co.sindi.oauth.core.http.HttpQueryParameterCodec;

/**
 * @author Buhake Sindi
 * @since 28 February 2012
 *
 */
public abstract class UriQueryParameterHttpRequest extends AbstractHttpRequest {

	private HttpParameters httpParameters;
	
	/**
	 * @param method
	 * @param uri
	 */
	protected UriQueryParameterHttpRequest(String method, URI uri) {
		super(method, uri);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param httpParameters the httpParameters to set
	 */
	public void setHttpParameters(HttpParameters httpParameters) {
		this.httpParameters = httpParameters;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.AbstractHttpRequest#getURI()
	 */
	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		String uri = super.getURI().toString();
		if (httpParameters != null && !httpParameters.isEmpty()) {
			String queryString = httpParameters.toString(new HttpQueryParameterCodec(new WWWFormURLEncodingCodec())); //new HttpQueryParameterPairer().toString(httpParameters.toMap());
			if (uri.indexOf('?') > -1) {
				uri += "&";
			} else {
				uri += "?";
			}
			
			uri += queryString;
		}

		try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
	}
}
