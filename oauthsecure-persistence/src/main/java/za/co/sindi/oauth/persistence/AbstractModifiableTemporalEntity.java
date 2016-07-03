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
public abstract class AbstractModifiableTemporalEntity<PK extends Serializable> extends AbstractCreationalTemporalEntity<PK> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2317445686346291931L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED_ON", insertable=false, updatable=false)
	private Date lastModificationTimestamp;

	/* (non-Javadoc)
	 * @see za.co.sindi.entity.AbstractIdentifiableAuditableEntity#getLastModificationTimestamp()
	 */
	@Override
	public Date getLastModificationTimestamp() {
		// TODO Auto-generated method stub
		return lastModificationTimestamp;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.entity.AbstractIdentifiableAuditableEntity#setLastModificationTimestamp(java.util.Date)
	 */
	@Override
	public void setLastModificationTimestamp(Date lastModificationTimestamp) {
		// TODO Auto-generated method stub
		this.lastModificationTimestamp = lastModificationTimestamp;
	}
}
