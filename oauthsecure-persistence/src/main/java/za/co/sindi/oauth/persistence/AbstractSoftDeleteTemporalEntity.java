/**
 * 
 */
package za.co.sindi.oauth.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Bienfait Sindi
 * @since 28 March 2016
 *
 */
@MappedSuperclass
public abstract class AbstractSoftDeleteTemporalEntity<PK extends Serializable> extends AbstractModifiableTemporalEntity<PK> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2020520470230199639L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DELETED_ON", insertable=false)
	private Date deletionTimestamp;

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.audit.TemporalAudit#getDeletionTimestamp()
	 */
	@Override
	public Date getDeletionTimestamp() {
		// TODO Auto-generated method stub
		return deletionTimestamp;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.persistence.audit.TemporalAudit#setDeletionTimestamp(java.util.Date)
	 */
	@Override
	public void setDeletionTimestamp(Date deletionTimestamp) {
		// TODO Auto-generated method stub
		this.deletionTimestamp = deletionTimestamp;
	}
}
