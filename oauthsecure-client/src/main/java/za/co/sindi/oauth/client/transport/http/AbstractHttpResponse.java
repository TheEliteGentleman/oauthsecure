/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.io.InputStream;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public abstract class AbstractHttpResponse extends AbstractMessageHeader implements HttpResponse {
	
	private int majorVersion;
	private int minorVersion;
	private int statusCode;
	private String reasonPhrase;
	private InputStream responseStream;
	
	/**
	 * @param majorVersion
	 * @param minorVersion
	 * @param statusCode
	 * @param reasonPhrase
	 * @param responseStream
	 */
	protected AbstractHttpResponse(int majorVersion, int minorVersion, int statusCode, String reasonPhrase, InputStream responseStream) {
		super();
		if (majorVersion < 0) {
			throw new IllegalArgumentException("Major version cannot be negative.");
		}
		
		if (minorVersion < 0) {
			throw new IllegalArgumentException("Minor version cannot be negative.");
		}
		
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.responseStream = responseStream;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.Response#getResponseStream()
	 */
	@Override
	public InputStream getResponseStream() {
		// TODO Auto-generated method stub
		return responseStream;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpResponse#getMajorVersion()
	 */
	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return majorVersion;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpResponse#getMinorVersion()
	 */
	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return minorVersion;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpResponse#getStatusCode()
	 */
	@Override
	public int getStatusCode() {
		// TODO Auto-generated method stub
		return statusCode;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpResponse#getReasonPhrase()
	 */
	@Override
	public String getReasonPhrase() {
		// TODO Auto-generated method stub
		return reasonPhrase;
	}
}
