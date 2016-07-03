/**
 * 
 */
package za.co.sindi.oauth.server.context;

/**
 * @author Bienfait Sindi
 * @since 15 November 2014
 *
 */
public interface OAuthContextFactory {

	public OAuthContext createOAuthContext(Object request, Object response);
//	public OAuthContext createOAuthContext(ApplicationContext application, RequestContext request, ResponseContext response);
}
