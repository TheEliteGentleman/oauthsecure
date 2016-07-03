/**
 * 
 */
package za.co.sindi.oauth.client.parameters;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is a generic OAuthParameters class.
 * All {@link Collections} and {@link Map} returned from this class are unmodifiable.
 * 
 * @author Buhake Sindi
 * @since 27 January 2012
 *
 */
public abstract class OAuthParameters {

	private Map<String, String> parameterMap;

	/**
	 * 
	 */
	public OAuthParameters() {
		super();
		// TODO Auto-generated constructor stub
		parameterMap = new LinkedHashMap<String, String>();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	public int size() {
		// TODO Auto-generated method stub
		return parameterMap.size();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return parameterMap.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return parameterMap.containsKey(key);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(String value) {
		// TODO Auto-generated method stub
		return parameterMap.containsValue(value);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	protected String get(String key) {
		// TODO Auto-generated method stub
		return parameterMap.get(key);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	protected String put(String key, String value) {
		// TODO Auto-generated method stub
		if (key != null && !key.isEmpty() && value != null) {
			return parameterMap.put(key, value);
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public String remove(String key) {
		// TODO Auto-generated method stub
		return parameterMap.remove(key);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	protected void putAll(OAuthParameters parameters) {
		// TODO Auto-generated method stub
		this.parameterMap.putAll(parameters.parameterMap);
	}

	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		// TODO Auto-generated method stub
		parameterMap.clear();
	}

	/**
	 * This returns an unmodifiable map of Oauth parameters stored.
	 * @return
	 */
	public Map<String, String> toMap() {
		return Collections.unmodifiableMap(parameterMap);
	}
}
