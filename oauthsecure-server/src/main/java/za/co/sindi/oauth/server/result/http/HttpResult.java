/**
 * 
 */
package za.co.sindi.oauth.server.result.http;

import za.co.sindi.oauth.server.result.Result;

/**
 * @author Buhake Sindi
 * @since 05 May 2012
 *
 */
public interface HttpResult extends Result {

	public void addHeader(String name, String value);
//	public void setStatusCode(int statusCode);
}
