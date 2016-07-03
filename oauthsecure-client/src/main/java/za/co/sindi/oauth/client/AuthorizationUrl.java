/**
 * 
 */
package za.co.sindi.oauth.client;

import java.net.URI;

import za.co.sindi.oauth.client.exception.OAuthRequestException;


/**
 * @author Buhake Sindi
 * @since 02 February 2012
 *
 */
public interface AuthorizationUrl {

	public String generateUrl() throws OAuthRequestException;
	public URI generateURI() throws OAuthRequestException;
}
