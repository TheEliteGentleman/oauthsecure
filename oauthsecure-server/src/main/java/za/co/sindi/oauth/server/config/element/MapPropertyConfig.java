/**
 * 
 */
package za.co.sindi.oauth.server.config.element;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Bienfait Sindi
 * @since 12 October 2014
 *
 */
public class MapPropertyConfig extends PropertyConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 207881328599816635L;
	
	private Set<EntryConfig> entryConfigs;

	/**
	 * @return the entryConfigs
	 */
	public Set<EntryConfig> getEntryConfigs() {
		return entryConfigs;
	}

	/**
	 * @param entryConfigs the entryConfigs to set
	 */
	public void setEntryConfigs(Set<EntryConfig> entryConfigs) {
		this.entryConfigs = entryConfigs;
	}

	public void addEntryConfig(EntryConfig entryConfig) {
		if (entryConfig == null) {
			return ;
		}
		
		if (entryConfigs == null) {
			entryConfigs = new LinkedHashSet<EntryConfig>();
		}
		
		entryConfigs.add(entryConfig);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((entryConfigs == null) ? 0 : entryConfigs.hashCode());
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
		MapPropertyConfig other = (MapPropertyConfig) obj;
		if (entryConfigs == null) {
			if (other.entryConfigs != null)
				return false;
		} else if (!entryConfigs.equals(other.entryConfigs))
			return false;
		return true;
	}
}
