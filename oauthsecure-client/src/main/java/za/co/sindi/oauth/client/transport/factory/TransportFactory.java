/**
 * 
 */
package za.co.sindi.oauth.client.transport.factory;

import za.co.sindi.oauth.client.transport.Request;
import za.co.sindi.oauth.client.transport.Response;
import za.co.sindi.oauth.client.transport.Transport;
import za.co.sindi.oauth.client.transport.exception.TransportFactoryException;

/**
 * @author Buhake Sindi
 * @since 23 February 2012
 *
 */
public interface TransportFactory<REQ extends Request, RES extends Response> {

	public void registerTransport(String id, Class<? extends Transport<REQ, RES>> transportClass);
	public void unregisterTransport(String id);
	public Transport<REQ, RES> createTransport() throws TransportFactoryException;
	public Transport<REQ, RES> createTransport(String id) throws TransportFactoryException;
	public REQ createRequest(String method, String path) throws TransportFactoryException;
}
