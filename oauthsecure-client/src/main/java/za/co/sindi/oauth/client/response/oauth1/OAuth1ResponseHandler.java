/**
 * 
 */
package za.co.sindi.oauth.client.response.oauth1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Level;

import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.response.AbstractResponseHandler;
import za.co.sindi.oauth.client.transport.http.HttpResponse;
import za.co.sindi.oauth.client.transport.http.util.ResponseUtils;
import za.co.sindi.oauth.util.IOUtils;

/**
 * @author Buhake Sindi
 * @since 17 February 2012
 *
 */
public abstract class OAuth1ResponseHandler<T> extends AbstractResponseHandler<T> {
	
	/**
	 * This method should be overridden if the user wants to handle {@link HttpResponse}.
	 * 
	 * @param response an HTTP response.
	 * @return  
	 * @throws OAuthResponseException
	 */
	@Override
	protected T handleHttpResponse(HttpResponse response) throws OAuthResponseException {
		try {
			int statusCode = response.getStatusCode();
			String charset = ResponseUtils.getCharset(response);
			String contentType = ResponseUtils.getContentType(response);
			String message = IOUtils.toString(response.getResponseStream(), charset);
			
			if ("application/x-www-form-urlencoded".equals(contentType)) {
				message = URLDecoder.decode(message, charset);
			}
			
			if (statusCode >= 400) {
				throw new OAuthResponseException("HTTP Status " + statusCode + " - " + response.getReasonPhrase() + ":" + LINE_SEPARATOR + message);
			}
			
			if (statusCode != 200) {
				if (logger.isEnabledFor(Level.WARN)) {
					logger.warn("WARN: HTTP Status code " + statusCode + " received.");
				}
			}
			
			if (message.split("\r\n|\r|\n").length > 1) {//First line always end with a \r\n
				throw new OAuthResponseException("OAuth Error:" + LINE_SEPARATOR + message);
			}
			
			//Do we have callback?
			int questionMarkIndex = message.indexOf('?'); 
			if (questionMarkIndex > -1) {
				message = message.substring(questionMarkIndex + 1);
			}
			
			Map<String, String> attributeMap = new LinkedHashMap<String, String>();
			for (String t : message.split("&")) {
				int equalIndex = t.indexOf('=');
				
				String name = t.substring(0, equalIndex);
				String value = t.substring(equalIndex + 1);
				attributeMap.put(name, value);
			}
			
			return handleOAuthResponse(Collections.unmodifiableMap(attributeMap));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthResponseException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new OAuthResponseException(e);
		} finally {
			try {
				response.getResponseStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("Error closing HTTP Response Stream.");
			}
		}
	}
	
	protected abstract T handleOAuthResponse(Map<String, String> oauthParameters) throws OAuthResponseException;
}
