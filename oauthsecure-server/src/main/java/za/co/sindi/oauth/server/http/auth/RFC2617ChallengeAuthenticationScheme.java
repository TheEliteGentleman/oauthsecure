/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public abstract class RFC2617ChallengeAuthenticationScheme extends AbstractChallengeAuthenticationScheme implements ChallengeAuthenticationScheme {

	private String realm;

	/**
	 * @param schemeName
	 * @param responseHeaderName
	 * @param realm
	 */
	public RFC2617ChallengeAuthenticationScheme(String schemeName, String responseHeaderName, String realm) {
		super(schemeName, responseHeaderName);
		this.realm = realm;
	}
	
	/**
	 * @return the realm
	 */
	protected String getRealm() {
		return realm;
	}
}