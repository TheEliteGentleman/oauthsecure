/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.header;

import za.co.sindi.oauth.client.transport.http.Header;


/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public class BasicHeader implements Header {

	private String name;
	private String value;
	
	/**
	 * @param name
	 * @param value
	 */
	public BasicHeader(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public BasicHeader(String name, int value) {
		super();
		this.name = name;
		this.value = String.valueOf(value);
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public BasicHeader(String name, double value) {
		super();
		this.name = name;
		this.value = String.valueOf(value);
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public BasicHeader(String name, float value) {
		super();
		this.name = name;
		this.value = String.valueOf(value);
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public BasicHeader(String name, short value) {
		super();
		this.name = name;
		this.value = String.valueOf(value);
	}
	
	/**
	 * @param name
	 * @param value
	 */
	public BasicHeader(String name, boolean value) {
		super();
		this.name = name;
		this.value = String.valueOf(value);
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Header#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	/* (non-Javadoc)
	 * @see net.oauth.transport.http.Header#getValue()
	 */
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
