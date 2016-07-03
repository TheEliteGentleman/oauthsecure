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
 * @since 29 March 2016
 *
 */
@MappedSuperclass
public abstract class AbstractCreationalTemporalEntity<PK extends Serializable> extends AbstractTemporalEntity<PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4251182838494720548L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON", insertable=false, updatable=false, nullable=false)
	private Date creationTimestamp;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.entity.AbstractIdentifiableTemporalEntity#getCreationTimestamp()
	 */
	@Override
	public Date getCreationTimestamp() {
		// TODO Auto-generated method stub
		return creationTimestamp;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.entity.AbstractIdentifiableTemporalEntity#setCreationTimestamp(java.util.Date)
	 */
	@Override
	public void setCreationTimestamp(Date creationTimestamp) {
		// TODO Auto-generated method stub
		this.creationTimestamp = creationTimestamp;
	}
}
