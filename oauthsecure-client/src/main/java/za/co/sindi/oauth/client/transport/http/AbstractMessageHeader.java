/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Buhake Sindi
 * @since 25 March 2012
 *
 */
public abstract class AbstractMessageHeader implements MessageHeader {

	private List<Header> headers;
	
	/**
	 * Default Constructor
	 */
	protected AbstractMessageHeader() {
		// TODO Auto-generated constructor stub
		headers = new ArrayList<Header>();
	}
	
	/* (non-Javadoc)
	 * @see net.oauth.transport.http.MessageHeader#addHeader(net.oauth.transport.http.Header)
	 */
	@Override
	public void addHeader(Header header) {
		// TODO Auto-generated method stub
		if (header == null) {
			throw new IllegalArgumentException("Header is null.");
		}
		
		removeHeader(header);
		headers.add(header);
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.MessageHeader#getHeader(java.lang.String)
	 */
	@Override
	public Header getHeader(String name) {
		// TODO Auto-generated method stub
		if (name != null) {
			for (Header header : headers) {
				if (header.getName().equals(name)) {
					return header;
				}
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.MessageHeader#containsHeader(java.lang.String)
	 */
	@Override
	public boolean containsHeader(String name) {
		// TODO Auto-generated method stub
		if (name != null) {
			for (Header header : headers) {
				if (header.getName().equals(name)) {
					return true;
				}
			}
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.MessageHeader#removeHeader(net.oauth.transport.http.Header)
	 */
	@Override
	public void removeHeader(Header header) {
		// TODO Auto-generated method stub
		if (header == null) {
			throw new IllegalArgumentException("Header is null.");
		}
		
		for (Iterator<Header> iter = headers.iterator(); iter.hasNext(); ) {
			Header _header = iter.next();
			if (_header.getName().equals(header.getName())) {
				iter.remove();
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.MessageHeader#getHeaders()
	 */
	@Override
	public Header[] getHeaders() {
		// TODO Auto-generated method stub
		return headers.toArray(new Header[headers.size()]);
	}
}
