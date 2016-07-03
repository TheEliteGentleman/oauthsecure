/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * @author Bienfait Sindi
 * @since 08 January 2016
 *
 */
public abstract class AbstractAuthenticationScheme implements AuthenticationScheme {

	private final String schemeName;
	
	/**
	 * @param schemeName
	 */
	protected AbstractAuthenticationScheme(String schemeName) {
		super();
		if (schemeName == null || schemeName.isEmpty()) {
			throw new IllegalArgumentException("A authentication scheme name may not be null.");
		}
		this.schemeName = schemeName;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.AuthenticationScheme#getSchemeName()
	 */
	@Override
	public final String getSchemeName() {
		// TODO Auto-generated method stub
		return schemeName;
	}
}
