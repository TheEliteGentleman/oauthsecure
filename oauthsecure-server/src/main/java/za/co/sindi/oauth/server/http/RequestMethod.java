/**
 * 
 */
package za.co.sindi.oauth.server.http;

import za.co.sindi.oauth.core.http.HttpConstants;

/**
 * @author Bienfait Sindi
 * @since 01 October 2014
 *
 */
public enum RequestMethod {
	CONNECT(HttpConstants.METHOD_CONNECT),
	DELETE(HttpConstants.METHOD_DELETE),
	GET(HttpConstants.METHOD_GET),
	HEAD(HttpConstants.METHOD_HEAD),
	OPTIONS(HttpConstants.METHOD_OPTIONS),
	PATCH(HttpConstants.METHOD_PATCH),
	POST(HttpConstants.METHOD_POST),
	PUT(HttpConstants.METHOD_PUT),
	TRACE(HttpConstants.METHOD_TRACE)
	;
	private final String method;

	/**
	 * @param method
	 */
	private RequestMethod(final String method) {
		this.method = method;
	}
	
	public static final RequestMethod of(final String method) {
		for (RequestMethod httpMethod : values()) {
			if (httpMethod.method.equalsIgnoreCase(method)) {
				return httpMethod;
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return method;
	}
}
