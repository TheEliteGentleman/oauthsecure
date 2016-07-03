/**
 * 
 */
package za.co.sindi.oauth.util;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public class OAuth2Util {

	private OAuth2Util() {
		//TODO Nothing
	}
	
	/**
	 * Normalises URL according to section 3 of Oauth 2 Draft 23.
	 * 
	 * @param url
	 * @return
	 */
	public static String normalizeUrl(String url) {
		if (url != null && !url.isEmpty()) {		
			int fragmentPos = url.indexOf("#");
			if (fragmentPos > -1) {
				return url.substring(0, fragmentPos);
			}
		}
		
		return url;
	}
}
