/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

/**
 * @author Bienfait Sindi
 * @since 18 January 2016
 *
 */
public interface OAuth1Credentials extends Credentials {

	public String getConsumerKey();
	public OAuthSignatureMethod getSignatureMethod();
	public Long getTimestamp();
	public String getNonce();
	public String getSignature();
	public String getVersion();
}
