/**
 * 
 */
package za.co.sindi.oauth.jaxrs;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import za.co.sindi.oauth.server.http.auth.AuthSchemes;

/**
 * @author Bienfait Sindi
 * @since 24 September 2015
 *
 */
public class OAuth1SecurityContext implements SecurityContext {

	/* (non-Javadoc)
	 * @see javax.ws.rs.core.SecurityContext#getUserPrincipal()
	 */
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.core.SecurityContext#isUserInRole(java.lang.String)
	 */
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.core.SecurityContext#isSecure()
	 */
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.core.SecurityContext#getAuthenticationScheme()
	 */
	public String getAuthenticationScheme() {
		// TODO Auto-generated method stub
		return AuthSchemes.OAUTH;
	}
}
