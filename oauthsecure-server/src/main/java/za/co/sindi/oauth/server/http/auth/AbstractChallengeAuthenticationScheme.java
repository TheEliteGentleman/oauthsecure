/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import java.io.IOException;

import za.co.sindi.oauth.server.context.http.HttpResponseContext;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public abstract class AbstractChallengeAuthenticationScheme extends AbstractAuthenticationScheme	implements ChallengeAuthenticationScheme {

	private String responseHeaderName;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.AuthenticationSchemeChallenge#sendAuthenticateResponse(za.co.sindi.oauth.server.context.http.HttpResponseContext)
	 */
	/**
	 * @param schemeName
	 * @param responseHeaderName
	 */
	protected AbstractChallengeAuthenticationScheme(String schemeName, String responseHeaderName) {
		super(schemeName);
		if (responseHeaderName == null || responseHeaderName.isEmpty()) {
			throw new IllegalArgumentException("No response header name was specified.");
		}
		
		this.responseHeaderName = responseHeaderName;
	}

	@Override
	public void sendAuthenticateResponse(HttpResponseContext response) throws IOException {
		// TODO Auto-generated method stub
		if (response == null) {
			throw new IllegalArgumentException("A HttpResponseContext may not be null");
		}
		
		response.addHeader(responseHeaderName, getSchemeName().trim() + " " + generateChallenge());
		response.sendError(getResponseStatusCode());
	}
	
	protected abstract int getResponseStatusCode();
	protected abstract String generateChallenge();
}
