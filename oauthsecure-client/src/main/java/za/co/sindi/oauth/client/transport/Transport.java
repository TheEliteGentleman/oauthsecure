/**
 * 
 */
package za.co.sindi.oauth.client.transport;

import za.co.sindi.oauth.client.transport.exception.TransportException;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public interface Transport<REQ extends Request, RES extends Response> {
	
	public RES send(REQ request) throws TransportException;
}
