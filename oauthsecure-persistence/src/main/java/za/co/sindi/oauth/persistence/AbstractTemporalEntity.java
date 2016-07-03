/**
 * 
 */
package za.co.sindi.oauth.persistence;

import java.io.Serializable;
import java.util.Date;

import za.co.sindi.persistence.audit.TemporalAudit;
import za.co.sindi.persistence.entity.AbstractEntity;

/**
 * @author Bienfait Sindi
 * @since 28 March 2016
 *
 */
public abstract class AbstractTemporalEntity<PK extends Serializable> extends AbstractEntity<PK> implements TemporalAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6612697375660921448L;

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.audit.TemporalAudit#getCreationTimestamp()
	 */
	public Date getCreationTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.audit.TemporalAudit#setCreationTimestamp(java.util.Date)
	 */
	public void setCreationTimestamp(Date creationTimestamp) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.audit.TemporalAudit#getLastModificationTimestamp()
	 */
	public Date getLastModificationTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.audit.TemporalAudit#setLastModificationTimestamp(java.util.Date)
	 */
	public void setLastModificationTimestamp(Date lastModificationTimestamp) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.audit.TemporalAudit#getDeletionTimestamp()
	 */
	@Override
	public Date getDeletionTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.audit.TemporalAudit#setDeletionTimestamp(java.util.Date)
	 */
	@Override
	public void setDeletionTimestamp(Date deletionTimestamp) {
		// TODO Auto-generated method stub
		
	}
}
