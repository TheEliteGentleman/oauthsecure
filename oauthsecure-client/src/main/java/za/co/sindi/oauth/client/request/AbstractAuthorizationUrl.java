/**
 * 
 */
package za.co.sindi.oauth.client.request;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

import za.co.sindi.oauth.client.AuthorizationUrl;
import za.co.sindi.oauth.client.exception.OAuthRequestException;

/**
 * @author Buhake Sindi
 * @since 17 February 2012
 *
 */
public abstract class AbstractAuthorizationUrl implements AuthorizationUrl {

	protected final Logger logger = Logger.getLogger(this.getClass());
	private String authorizationUrl;
	
	/**
	 * @param authorizationUrl
	 */
	protected AbstractAuthorizationUrl(final String authorizationUrl) {
		super();
		if (authorizationUrl == null || authorizationUrl.isEmpty()) {
			throw new IllegalArgumentException("Authorization endpoint is required.");
		}
		
		this.authorizationUrl = authorizationUrl;
	}

	/**
	 * @return the authorizationUrl
	 */
	protected final String getAuthorizationUrl() {
		return authorizationUrl;
	}

	/* (non-Javadoc)
	 * @see net.oauth.client.OAuthAuthorizationRequest#generateURI()
	 */
	@Override
	public URI generateURI() throws OAuthRequestException {
		// TODO Auto-generated method stub
		try {
			return new URI(generateUrl());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new OAuthRequestException(e);
		}
	}
}
