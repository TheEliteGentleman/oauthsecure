/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.util;

import za.co.sindi.oauth.client.transport.http.HttpParameters;
import za.co.sindi.oauth.core.http.HttpQueryParameterCodec;
import za.co.sindi.oauth.core.util.HttpParameterCodec;

/**
 * @author Buhake Sindi
 * @since 08 February 2012
 *
 */
public final class HttpParameterUtil {

	private HttpParameterUtil() {
	}
	
	public static HttpParameters parseQueryString(String queryString) {
		if (queryString == null) {
			return null;
		}
		
		/**
		 * This works but using better code...
		 */
//		HttpParameters parameters = new HttpParameters();
//		String[] queryParameters = queryString.split("&");
//		for (String parameter : queryParameters) {
//			String[] kvs = parameter.split("=", 2);
//			String key = kvs[0];
//			String value = kvs[1];
//			
//			if (parameters.getParameter(key) == null) {
//				parameters.setParameter(key, value);
//			} else {
//				parameters.getParameter(key).addValue(value);
//			}
//		}
//		
//		return parameters;
		
		HttpParameterCodec codec = new HttpQueryParameterCodec();
		return new HttpParameters(codec.decode(queryString));
	}
}
