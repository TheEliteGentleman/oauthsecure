/**
 * 
 */
package za.co.sindi.oauth.core.http.cookie.impl;


/**
 * @author Buhake Sindi
 * @since 24 March 2012
 *
 */
public class MacSetCookie extends HttpSetCookie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7263987203805070703L;
	private String macKey;
	private String macAlgorithm;
	
	/**
	 * @param sid
	 */
	public MacSetCookie(String sid) {
		super("SID", sid);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the macKey
	 */
	public String getMacKey() {
		return macKey;
	}

	/**
	 * @param macKey the macKey to set
	 */
	public void setMacKey(String macKey) {
		this.macKey = macKey;
	}

	/**
	 * @return the macAlgorithm
	 */
	public String getMacAlgorithm() {
		return macAlgorithm;
	}

	/**
	 * @param macAlgorithm the macAlgorithm to set
	 */
	public void setMacAlgorithm(String macAlgorithm) {
		this.macAlgorithm = macAlgorithm;
	}
}
