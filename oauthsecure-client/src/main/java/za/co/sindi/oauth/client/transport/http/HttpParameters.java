/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import za.co.sindi.oauth.client.transport.http.parameter.BasicHttpParameter;
import za.co.sindi.oauth.core.util.HttpParameterCodec;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public class HttpParameters {

	private List<HttpParameter> parameters;
	
	public HttpParameters() {
		super();
		parameters = new ArrayList<HttpParameter>();
	}

	/**
	 * @param parameters
	 */
	public HttpParameters(List<HttpParameter> parameters) {
		this();
		if (parameters != null) {
			//Rather swallow all instead of keeping reference.
			this.parameters.addAll(parameters);
		}
	}
	
	public HttpParameters(Map<String, String[]> parameters) {
		this();
		if (parameters != null) {
			for (Entry<String, String[]> entry : parameters.entrySet()) {
				HttpParameter parameter = null;
				String[] values = entry.getValue();
				if (values != null && values.length > 0) {
					int startIndex = 0;
					if (parameter == null) {
						parameter = new BasicHttpParameter(entry.getKey(), values[0]);
						startIndex = 1;
					}
					
					for (int i = startIndex; i < values.length; i++) {
						parameter.addValue(values[i]);
					}
				}
				
				this.parameters.add(parameter);
			}
		}
	}
	
	public void clear() {
		parameters.clear(); 
	}
	
	public boolean isEmpty() {
		return parameters.isEmpty();
	}
	
//	public String[] getParameter(String name) {
//		for (HttpParameter parameter : parameters) {
//			if (parameter.getName().equals(name)) {
//				return parameter.getValues();
//			}
//		}
//		
//		return null;
//	}
	
	public String[] removeParameter(String name) {
		String[] values = null;
		for (Iterator<HttpParameter> iter = parameters.iterator(); iter.hasNext(); ) {
			HttpParameter parameter = iter.next();
			if (parameter.getName().equals(name)) {
				values = parameter.getValues();
				
				//remove
				iter.remove();
			}
		}
		
		return values;
	}
	
	public void setParameter(String name, String value) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Parameter name is null or empty.");
		}
		
		if (value == null) {
			throw new IllegalArgumentException("Parameter value must not be null.");
		}
		
		//Add
		parameters.add(new BasicHttpParameter(name, value));
	}
	
	public HttpParameter getParameter(String name) {
		for (HttpParameter parameter : parameters) {
			if (parameter.getName().equals(name)) {
				return parameter;
			}
		}
		
		return null;
	}
	
	public HttpParameter[] getParameters() {
		return parameters.toArray(new HttpParameter[parameters.size()]);
	}
	
	public void setParameter(String name, int value) {
		setParameter(name, String.valueOf(value));
	}
	
	public void setParameter(String name, double value) {
		setParameter(name, String.valueOf(value));
	}
	
	public void setParameter(String name, long value) {
		setParameter(name, String.valueOf(value));
	}
	
	public void setParameter(String name, float value) {
		setParameter(name, String.valueOf(value));
	}
	
	public void setParameter(String name, short value) {
		setParameter(name, String.valueOf(value));
	}
	
	public void setParameter(String name, byte value) {
		setParameter(name, String.valueOf(value));
	}
	
	/**
	 * Return an unmodifiable map of the stored HTTP parameters.
	 * 
	 * @return
	 */
	public Map<String, String[]> toMap() {
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
		if (getParameters() != null) {
			for (HttpParameter parameter : getParameters()) {
				map.put(parameter.getName(), parameter.getValues());
			}
		}
		
		return Collections.unmodifiableMap(map);
	}
	
	public String toString(HttpParameterCodec codec) {
		if (codec == null) {
			throw new IllegalArgumentException("HttpParameterCodec may not be null.");
		}
		
		return codec.encode(toMap());
	}
}
