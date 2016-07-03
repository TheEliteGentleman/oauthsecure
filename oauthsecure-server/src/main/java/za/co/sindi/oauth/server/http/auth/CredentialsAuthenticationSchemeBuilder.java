/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public abstract class CredentialsAuthenticationSchemeBuilder<C extends Credentials, T extends CredentialsAuthenticationScheme<C>> implements AuthenticationSchemeBuilder<T> {

	private String requestHeaderName;

	/**
	 * @param requestHeaderName
	 */
	protected CredentialsAuthenticationSchemeBuilder(String requestHeaderName) {
		super();
		if (requestHeaderName == null || requestHeaderName.isEmpty()) {
			throw new IllegalArgumentException("No request header has been specified.");
		}
		
		this.requestHeaderName = requestHeaderName;
	}

	/**
	 * @return the requestHeaderName
	 */
	public String getRequestHeaderName() {
		return requestHeaderName;
	}
}
