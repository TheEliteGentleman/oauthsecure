/**
 * 
 */
package za.co.sindi.oauth.server.result.http;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Buhake Sindi
 * @since 07 May 2012
 *
 */
public abstract class AbstractHttpResult implements HttpResult {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private Map<String, String> headerMap = new LinkedHashMap<String, String>();
//	private int statusCode;

	/* (non-Javadoc)
	 * @see com.neurologic.oauth.core.result.HttpResult#addHeader(java.lang.String, java.lang.String)
	 */
	@Override
	public void addHeader(String name, String value) {
		// TODO Auto-generated method stub
		headerMap.put(name, value);
	}
	
	/**
	 * Returns a {@link Set} of header map entries.
	 * @return
	 */
	protected Set<Entry<String, String>> headersEntrySet() {
		return headerMap.entrySet();
	}

//	/* (non-Javadoc)
//	 * @see com.neurologic.oauth.core.result.HttpResult#setStatusCode(int)
//	 */
//	@Override
//	public void setStatusCode(int statusCode) {
//		// TODO Auto-generated method stub
//		this.statusCode = statusCode;
//	}
//
//	/**
//	 * @return the statusCode
//	 */
//	protected int getStatusCode() {
//		return statusCode;
//	}
}
