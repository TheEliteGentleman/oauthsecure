/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.io.Closeable;

import za.co.sindi.oauth.client.transport.Transport;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public interface HttpTransport extends Transport<HttpRequest, HttpResponse>, Closeable {

}
