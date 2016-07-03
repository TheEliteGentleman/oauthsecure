/**
 * 
 */
package za.co.sindi.oauth.server.config.element;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Bienfait Sindi
 * @since 12 October 2014
 *
 */
public class BeanConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -632480769337932487L;
	
	private String id;
	private String className;
	private Set<PropertyConfig> propertyConfigs;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the propertyConfigs
	 */
	public Set<PropertyConfig> getPropertyConfigs() {
		return propertyConfigs;
	}

	/**
	 * @param propertyConfigs the propertyConfigs to set
	 */
	public void setPropertyConfigs(Set<PropertyConfig> propertyConfigs) {
		this.propertyConfigs = propertyConfigs == null ? Collections.<PropertyConfig>emptySet() : propertyConfigs;
	}

	/**
	 * @param add property to the properties set
	 */
	public void addPropertyConfig(PropertyConfig propertyConfig) {
		if (propertyConfig == null) {
			return;
		}
		
		if (propertyConfigs == null) {
			propertyConfigs = new LinkedHashSet<PropertyConfig>();
		}
		
		propertyConfigs.add(propertyConfig);
	}
	
	public PropertyConfig findPropertyConfig(String name) {
		if (name != null && !name.isEmpty()) {
			for (PropertyConfig propertyConfig : getPropertyConfigs()) {
				if (name.equals(propertyConfig.getName())) {
					return propertyConfig;
				}
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((propertyConfigs == null) ? 0 : propertyConfigs.hashCode());
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
		BeanConfig other = (BeanConfig) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (propertyConfigs == null) {
			if (other.propertyConfigs != null)
				return false;
		} else if (!propertyConfigs.equals(other.propertyConfigs))
			return false;
		return true;
	}
}
