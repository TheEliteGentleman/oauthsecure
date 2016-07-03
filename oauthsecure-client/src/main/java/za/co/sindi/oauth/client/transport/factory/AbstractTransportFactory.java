/**
 * 
 */
package za.co.sindi.oauth.client.transport.factory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import za.co.sindi.oauth.client.transport.Request;
import za.co.sindi.oauth.client.transport.Response;
import za.co.sindi.oauth.client.transport.Transport;
import za.co.sindi.oauth.client.transport.exception.TransportFactoryException;

/**
 * @author Buhake Sindi
 * @since 07 March 2012
 *
 */
public abstract class AbstractTransportFactory<REQ extends Request, RES extends Response> implements TransportFactory<REQ, RES> {

	protected final Logger logger = Logger.getLogger(this.getClass());
	private Map<String, Class<? extends Transport<REQ, RES>>> transportMap = Collections.synchronizedMap(new LinkedHashMap<String, Class<? extends Transport<REQ, RES>>>());
	
	/* (non-Javadoc)
	 * @see net.oauth.transport.factory.TransportFactory#registerTransport(java.lang.String, java.lang.Class)
	 */
	@Override
	public void registerTransport(String id, Class<? extends Transport<REQ, RES>> transportClass) {
		// TODO Auto-generated method stub
		if (transportMap.containsKey(id)) {
			throw new IllegalArgumentException("Transport with ID '" + id + "' already exists.");
		}
		
		transportMap.put(id, transportClass);
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.factory.TransportFactory#unregisterTransport(java.lang.String)
	 */
	@Override
	public void unregisterTransport(String id) {
		// TODO Auto-generated method stub
		if (!transportMap.containsKey(id)) {
			throw new IllegalArgumentException("Transport with ID '" + id + "' doesn't exists.");
		}
		
		transportMap.remove(id);
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.factory.TransportFactory#createTransport(java.lang.String)
	 */
	@Override
	public Transport<REQ, RES> createTransport(String id) throws TransportFactoryException {
		// TODO Auto-generated method stub
		if (!transportMap.containsKey(id)) {
			throw new IllegalArgumentException("Transport with ID '" + id + "' doesn't exists.");
		}
		
		try {
			return transportMap.get(id).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new TransportFactoryException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new TransportFactoryException(e);
		}
	}
}
