/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.net.URI;


/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public abstract class EntityBasedHttpRequest extends AbstractHttpRequest {

	private HttpEntity entity;

	/**
	 * @param method
	 * @param uri
	 */
	protected EntityBasedHttpRequest(String method, URI uri) {
		super(method, uri);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the entity
	 */
	public HttpEntity getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}
}
