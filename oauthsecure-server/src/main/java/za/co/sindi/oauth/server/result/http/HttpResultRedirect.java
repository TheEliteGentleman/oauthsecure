/**
 * 
 */
package za.co.sindi.oauth.server.result.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.core.http.HttpConstants;
import za.co.sindi.oauth.core.http.HttpQueryParameterCodec;
import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.context.http.HttpResponseContext;
import za.co.sindi.oauth.server.exception.ResultException;

/**
 * @author Buhake Sindi
 * @since 07 May 2012
 *
 */
public class HttpResultRedirect extends AbstractHttpResult {

	private String location;
	private boolean contextRelative;
	private boolean http10Compatible;
	private Map<String, String[]> parameterMap;
	
	/**
	 * Default constructor (in case of <code>HttpResultRedirect.newInstance()</code>).
	 */
	public HttpResultRedirect() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param location
	 */
	public HttpResultRedirect(String location) {
		this(location, false);
	}

	/**
	 * @param location
	 * @param contextRelative
	 */
	public HttpResultRedirect(String location, boolean contextRelative) {
		this(location, contextRelative, false);
	}

	/**
	 * @param location
	 * @param contextRelative
	 * @param http10Compatible
	 */
	public HttpResultRedirect(String location, boolean contextRelative, boolean http10Compatible) {
		super();
		setLocation(location);
		setContextRelative(contextRelative);
		setHttp10Compatible(http10Compatible);
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param contextRelative the contextRelative to set
	 */
	public void setContextRelative(boolean contextRelative) {
		this.contextRelative = contextRelative;
	}

	/**
	 * @param http10Compatible the http10Compatible to set
	 */
	public void setHttp10Compatible(boolean http10Compatible) {
		this.http10Compatible = http10Compatible;
	}

	/**
	 * Add Query parameter.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public HttpResultRedirect addParameter(String key, String value) {
		String[] values = null;
		if (parameterMap == null) {
			parameterMap = new LinkedHashMap<String, String[]>();
		}
		
		if (parameterMap.containsKey(key)) {
			values = parameterMap.get(key);
			
			List<String> valueList = new ArrayList<String>(Arrays.asList(values));
			valueList.add(value);
			values = valueList.toArray(new String[values.length]);
		} else {
			values = new String[] {value};
		}
		
		if (values != null) {
			parameterMap.put(key, values);
		}
		
		return this;
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.result.Result#executeResult(za.co.sindi.oauth.server.context.OAuthContext)
	 */
	@Override
	public void executeResult(OAuthContext context) throws ResultException {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(context != null, "No OAuthContext was provided.");
		try {
			if (parameterMap != null && !parameterMap.isEmpty()) {
				int parameterStartPos = location.indexOf('?', 1);
				StringBuilder sb = new StringBuilder(location);
				if (parameterStartPos == -1) {
					sb.append("?");
				} else {
					sb.append("&");
				}
				sb.append(getParameterString());
				
				location = sb.toString();
			}
			
			if (location.startsWith("/") && contextRelative) {
				context.getContainer().forward(location);
			} else {
				HttpResponseContext responseContext = (HttpResponseContext) context.getContainer().getResponse();
				
				if (http10Compatible) {
					//We must do a HTTP 302 Found.
					responseContext.sendRedirect(responseContext.encodeRedirectURL(location));
				} else {
					responseContext.setStatusCode(HttpConstants.SC_SEE_OTHER); //HTTP 303
					responseContext.addHeader(HttpConstants.HEADER_LOCATION, responseContext.encodeRedirectURL(location));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ResultException(e);
		}
	}
	
	private String getParameterString() {
//		StringBuffer sb = new StringBuffer();
//		
//		if (parameterMap != null) {
//			for (Entry<String, String[]> entry : parameterMap.entrySet()) {
//				for (String value:  entry.getValue()) {
//					if (sb.length() > 0) {
//						sb.append("&");
//					}
//					
//					sb.append(entry.getKey()).append("=").append(value);
//				}
//			}
//		}
//		
//		return sb.toString();
		
		return new HttpQueryParameterCodec().encode(parameterMap);
	}
}
