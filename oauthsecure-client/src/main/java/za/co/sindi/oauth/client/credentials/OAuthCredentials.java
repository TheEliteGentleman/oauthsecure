/**
 * 
 */
package za.co.sindi.oauth.client.credentials;

import java.io.Serializable;

/**
 * @author Buhake Sindi
 * @since 29 January 2012
 *
 */
public interface OAuthCredentials extends Serializable {

	public int getOAuthVersion();
}
