/**
 * 
 */
package za.co.sindi.oauth.server.config.element;

/**
 * @author Bienfait Sindi
 * @since 12 October 2014
 *
 */
public class BeanReferencePropertyConfig extends PropertyConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2399638102747664321L;
	
	private String beanReferenceId;

	/**
	 * @return the beanReferenceId
	 */
	public String getBeanReferenceId() {
		return beanReferenceId;
	}

	/**
	 * @param beanReferenceId the beanReferenceId to set
	 */
	public void setBeanReferenceId(String beanReferenceId) {
		this.beanReferenceId = beanReferenceId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((beanReferenceId == null) ? 0 : beanReferenceId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanReferencePropertyConfig other = (BeanReferencePropertyConfig) obj;
		if (beanReferenceId == null) {
			if (other.beanReferenceId != null)
				return false;
		} else if (!beanReferenceId.equals(other.beanReferenceId))
			return false;
		return true;
	}
}
