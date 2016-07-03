/**
 * 
 */
package za.co.sindi.oauth.server.security;

import java.security.Principal;

/**
 * @author Bienfait Sindi
 * @siince 17 January 2016
 *
 */
public class UserPrincipal implements Principal {

	private final String username;
	
	/**
	 * @param username
	 */
	public UserPrincipal(String username) {
		super();
		if (username == null) {
			throw new IllegalArgumentException("A username may not be null.");
		}
		this.username = username;
	}


	/* (non-Javadoc)
	 * @see java.security.Principal#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return username;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPrincipal other = (UserPrincipal) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserPrincipal [username=" + username + "]";
	}
}
