/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import java.io.Serializable;
import java.security.Principal;

import za.co.sindi.oauth.server.security.UserPrincipal;

/**
 * @author Bienfait Sindi
 * @since 11 January 2016
 *
 */
public class UsernamePasswordCredentials implements Credentials, PrincipalCredentials, PasswordCredentials, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5275089332243671759L;
	
	private Principal userPrincipal;
	private String password;
	
	/**
	 * @param userPrincipal
	 * @param password
	 */
	public UsernamePasswordCredentials(final String userName, final String password) {
		super();
		this.userPrincipal = new UserPrincipal(userName);
		if (password == null) {
			throw new IllegalArgumentException("Password may not be null.");
		}
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.PasswordCredentials#getPassword()
	 */
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.Credentials#getUserPrincipal()
	 */
	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return userPrincipal;
	}
}
