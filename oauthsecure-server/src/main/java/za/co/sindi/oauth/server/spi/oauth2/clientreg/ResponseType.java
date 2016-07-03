/**
 * 
 */
package za.co.sindi.oauth.server.spi.oauth2.clientreg;

/**
 * @author Bienfait Sindi
 * @since 28 March 2016
 *
 */
public final class ResponseType {
	
	public static final ResponseType CODE = new ResponseType("code");
	public static final ResponseType TOKEN = new ResponseType("token");
	
	private final String type;

	/**
	 * @param type
	 */
	private ResponseType(String type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ResponseType other = (ResponseType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type;
	}
}
