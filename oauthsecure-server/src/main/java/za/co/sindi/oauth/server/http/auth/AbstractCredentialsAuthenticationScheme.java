/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.exception.HttpException;
import za.co.sindi.oauth.server.exception.http.MalformedChallengeException;

/**
 * @author Bienfait Sindi
 * @since 09 February 2016
 *
 */
public abstract class AbstractCredentialsAuthenticationScheme<T extends Credentials> extends AbstractAuthenticationScheme implements CredentialsAuthenticationScheme<T> {

	private String requestHeaderName;
	
	/**
	 * @param schemeName
	 * @param requestHeaderName
	 */
	protected AbstractCredentialsAuthenticationScheme(String schemeName, String requestHeaderName) {
		super(schemeName);
		if (requestHeaderName == null || requestHeaderName.isEmpty()) {
			throw new IllegalArgumentException("No request header has been specified.");
		}
		
		this.requestHeaderName = requestHeaderName;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.AuthenticationSchemeCredentials#extractCredentials(za.co.sindi.oauth.server.context.http.HttpRequestContext)
	 */
	@Override
	public T extractCredentials(HttpRequestContext request) throws HttpException {
		// TODO Auto-generated method stub
		if (request == null) {
			throw new IllegalArgumentException("A HttpRequestContext may not be null");
		}
		
		if (!request.containsHeader(requestHeaderName)) {
			throw new MalformedChallengeException("HTTP header '" + requestHeaderName + "' does not exist.");
		}
		
		String authorizationString = request.getHeader(requestHeaderName);
		if (authorizationString == null || authorizationString.isEmpty()) {
			throw new MalformedChallengeException("HTTP header '" + requestHeaderName + "' credential is required.");
		}
		
		authorizationString = authorizationString.trim(); //Removing any spaces, since we can't really trust all developers...
		String authenticationPrefix = getSchemeName().toLowerCase() + " ";
		if (!authorizationString.toLowerCase().startsWith(authenticationPrefix)) {
			throw new MalformedChallengeException("HTTP header '" + requestHeaderName + "' credentials does not start with the required scheme '" + getSchemeName() + " ' (space included). See RFC 2617)");
		}
		
		return extractCredentials(request, authorizationString.substring(authenticationPrefix.length()));
	}
	
	protected abstract T extractCredentials(HttpRequestContext request, final String authorizationString) throws HttpException;
}
