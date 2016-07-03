/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.parameter;

import java.util.ArrayList;
import java.util.List;

import za.co.sindi.oauth.client.transport.http.HttpParameter;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public class BasicHttpParameter implements HttpParameter {
	
	private String name;
	private List<String> values;

	/**
	 * @param name
	 * @param value
	 */
	public BasicHttpParameter(String name, String value) {
		super();
		this.name = name;
		this.values = new ArrayList<String>();
		values.add(value);
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpParameter#addValue(java.lang.String)
	 */
	@Override
	public HttpParameter addValue(String value) {
		// TODO Auto-generated method stub
		if (!values.contains(value)) {
			values.add(value);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpParameter#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpParameter#getValues()
	 */
	@Override
	public String[] getValues() {
		// TODO Auto-generated method stub
		return values.toArray(new String[values.size()]);
	}
}
