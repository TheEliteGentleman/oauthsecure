/**
 * 
 */
package za.co.sindi.oauth.client;

import za.co.sindi.oauth.client.exception.OAuthRequestException;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.transport.Request;
import za.co.sindi.oauth.client.transport.Response;
import za.co.sindi.oauth.client.transport.factory.TransportFactory;


/**
 * @author Buhake Sindi
 * @since 02 February 2012
 *
 */
public interface OAuthRequest<REQ extends Request, RES extends Response> {

	public String getRequestMethod();
	public String getRequestUrl();
	public <T> T execute() throws OAuthRequestException, OAuthResponseException;
	public <T> T execute(ResponseHandler<T> responseHandler) throws OAuthRequestException, OAuthResponseException;
	public void setTransportFactory(TransportFactory<REQ, RES> transportFactory);
}
