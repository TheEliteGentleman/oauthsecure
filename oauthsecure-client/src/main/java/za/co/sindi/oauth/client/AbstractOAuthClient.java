/**
 * 
 */
package za.co.sindi.oauth.client;

/**
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public abstract class AbstractOAuthClient<T extends OAuthEndpoints> implements OAuthClient {
	
	private T endpoints;

	/**
	 * @param endpoints
	 */
	protected AbstractOAuthClient(final T endpoints) {
		super();
		if (endpoints == null) {
			throw new IllegalArgumentException("A OAuth Endpoint is required.");
		}
		
		this.endpoints = endpoints;
	}

	/**
	 * @return the endpoints
	 */
	protected final T getEndpoints() {
		return endpoints;
	}
}
