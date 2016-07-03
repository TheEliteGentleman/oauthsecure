/**
 * 
 */
package za.co.sindi.oauth.server.config.element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bienfait Sindi
 * @since 12 October 2014
 *
 */
public class ServiceConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3662723146893069436L;
	
	private String id;
	private String address;
	private String beanReferenceId;
	private List<RequestConfig> requestConfigs;
	
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
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

	/**
	 * @return the requestConfigs
	 */
	public List<RequestConfig> getRequestConfigs() {
		return requestConfigs;
	}

	/**
	 * @param requestConfigs the requestConfigs to set
	 */
	public void setRequestConfigs(List<RequestConfig> requestConfigs) {
		this.requestConfigs = requestConfigs;
	}

	public void addRequestConfig(RequestConfig requestConfig) {
		if (requestConfig != null) {
			if (requestConfigs == null) {
				requestConfigs = new ArrayList<RequestConfig>();
			}
			
			requestConfigs.add(requestConfig);
		}
	}
	
	public RequestConfig findRequestConfig(String method) {
		if (method != null && !method.isEmpty() && requestConfigs != null) {
			for (RequestConfig requestConfig : requestConfigs) {
				if (method.equals(requestConfig.getMethod())) {
					return requestConfig;
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
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((beanReferenceId == null) ? 0 : beanReferenceId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((requestConfigs == null) ? 0 : requestConfigs.hashCode());
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
		ServiceConfig other = (ServiceConfig) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (beanReferenceId == null) {
			if (other.beanReferenceId != null)
				return false;
		} else if (!beanReferenceId.equals(other.beanReferenceId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (requestConfigs == null) {
			if (other.requestConfigs != null)
				return false;
		} else if (!requestConfigs.equals(other.requestConfigs))
			return false;
		return true;
	}
}
