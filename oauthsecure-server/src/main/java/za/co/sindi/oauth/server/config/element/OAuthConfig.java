/**
 * 
 */
package za.co.sindi.oauth.server.config.element;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bienfait Sindi
 * @since 10 October 2014
 *
 */
public class OAuthConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1037320093284134478L;
	
	private Map<String, BeanConfig> beanConfigs;
	private Map<String, ServiceConfig> serviceConfigs;
	
	public boolean addBeanConfig(BeanConfig beanConfig) {
		if (beanConfig == null) {
			return false;
		}
		
		if (beanConfigs == null) {
			beanConfigs = new HashMap<String, BeanConfig>();
		}
		
		beanConfigs.put(beanConfig.getId(), beanConfig);
		return true;
	}
	
	public boolean addServiceConfig(ServiceConfig serviceConfig) {
		if (serviceConfig == null) {
			return false;
		}
		
		if (serviceConfigs == null) {
			serviceConfigs = new HashMap<String, ServiceConfig>();
		}
		
		serviceConfigs.put(serviceConfig.getId(), serviceConfig);
		return true;
	}
	
	public BeanConfig findBeanConfig(String name) {
		if (name != null && !name.isEmpty() && beanConfigs != null) { 
			return beanConfigs.get(name);
		}
		
		return null;
	}
	
	public BeanConfig findBeanConfigByClassName(String className) {
		if (className != null && !className.isEmpty()) {
			for (BeanConfig beanConfig : getBeanConfigs()) {
				if (className.equals(beanConfig.getClassName())) {
					return beanConfig;
				}
			}
		}
		
		return null;
	}
	
	public ServiceConfig findServiceConfigByAddress(String address) {
		if (address != null && !address.isEmpty()) {
			for (ServiceConfig serviceConfig : getServiceConfigs()) {
				if (address.equals(serviceConfig.getAddress())) {
					return serviceConfig;
				}
			}
		}
		
		return null;
	}
	
	public ServiceConfig findServiceConfigById(String id) {
		if (id != null && !id.isEmpty() && serviceConfigs != null) {
			return serviceConfigs.get(id);
		}
		
		return null;
	}
	
	public Collection<BeanConfig> getBeanConfigs() {
		return (beanConfigs == null) ? Collections.<BeanConfig>emptyList() : beanConfigs.values();
	}
	
	public Collection<ServiceConfig> getServiceConfigs() {
		return (serviceConfigs == null) ? Collections.<ServiceConfig>emptyList() : serviceConfigs.values();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beanConfigs == null) ? 0 : beanConfigs.hashCode());
		result = prime * result
				+ ((serviceConfigs == null) ? 0 : serviceConfigs.hashCode());
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
		OAuthConfig other = (OAuthConfig) obj;
		if (beanConfigs == null) {
			if (other.beanConfigs != null)
				return false;
		} else if (!beanConfigs.equals(other.beanConfigs))
			return false;
		if (serviceConfigs == null) {
			if (other.serviceConfigs != null)
				return false;
		} else if (!serviceConfigs.equals(other.serviceConfigs))
			return false;
		return true;
	}
}
