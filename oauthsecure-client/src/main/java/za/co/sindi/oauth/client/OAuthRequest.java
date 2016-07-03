/**
 * 
 */
package za.co.sindi.oauth.client;

import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.exception.OAuthResponseException;


/**
 * @author Buhake Sindi
 * @since 02 February 2012
 *
 */
public interface OAuthRequest {

	public String getRequestMethod();
	public String getRequestUrl();
	public <T> T execute() throws OAuthRequestException, OAuthResponseException;
	public <T> T execute(ResponseHandler<T> responseHandler) throws OAuthRequestException, OAuthResponseException;
}
