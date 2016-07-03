/**
 * 
 */
package za.co.sindi.oauth.server.request;

import za.co.sindi.oauth.server.context.RequestContext;

/**
 * @author Bienfait Sindi
 * @since 13 February 2016
 *
 */
public interface OAuthRequest {

	public void execute(RequestContext request);
}
