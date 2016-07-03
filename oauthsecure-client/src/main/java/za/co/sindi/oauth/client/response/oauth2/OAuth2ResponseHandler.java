/**
 * 
 */
package za.co.sindi.oauth.client.response.oauth2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Level;

import za.co.sindi.oauth.client.exception.OAuth2ErrorResponseException;
import za.co.sindi.oauth.client.exception.OAuthResponseException;
import za.co.sindi.oauth.client.parameters.oauth2.OAuth2ErrorParameters;
import za.co.sindi.oauth.client.response.AbstractResponseHandler;
import za.co.sindi.oauth.client.transport.http.Header;
import za.co.sindi.oauth.client.transport.http.HttpResponse;
import za.co.sindi.oauth.client.transport.http.util.ResponseUtils;
import za.co.sindi.oauth.core.enums.OAuth2Error;
import za.co.sindi.oauth.core.http.HttpConstants;
import za.co.sindi.oauth.core.utils.OAuth2Constants;
import za.co.sindi.oauth.util.IOUtils;

/**
 * @author Buhake Sindi
 * @since 20 February 2012
 *
 */
public abstract class OAuth2ResponseHandler<T> extends AbstractResponseHandler<T> {

	private static final String CACHE_NO_STORE = "no-store";
	private static final String PRAGMA_NO_CACHE = "no-cache";
	protected static final String CONTENT_TYPE_JSON = "application/json";

	/* (non-Javadoc)
	 * @see net.oauth.client.response.AbstractOAuthResponseHandler#handleHttpResponse(net.oauth.transport.http.HttpResponse)
	 */
	@Override
	protected T handleHttpResponse(HttpResponse response) throws OAuthResponseException {
		// TODO Auto-generated method stub
		verifyResponseHeader(response, HttpConstants.HEADER_CACHE_CONTROL, CACHE_NO_STORE);
		verifyResponseHeader(response, HttpConstants.HEADER_PRAGMA, PRAGMA_NO_CACHE);
		
		String contentType = ResponseUtils.getContentType(response);
		if (!CONTENT_TYPE_JSON.equals(contentType)) {
			if (logger.isEnabledFor(Level.WARN)) {
				logger.warn("Content-Type=" + contentType + " (which isn't of type of " + CONTENT_TYPE_JSON + "). Attempting to use JSON anyway.");
			}
		}
		
		try {
			String entityBody = IOUtils.toString(response.getResponseStream(), ResponseUtils.getCharset(response));
			JSONObject json = (JSONObject) JSONSerializer.toJSON(entityBody);
			if (response.getStatusCode() >= 400) {
				OAuth2ErrorParameters errorParameters = new OAuth2ErrorParameters(OAuth2Error.of(json.getString(OAuth2Constants.ERROR)));
				if (json.containsKey(OAuth2Constants.ERROR_DESCRIPTION))
					errorParameters.setErrorDescription(json.getString(OAuth2Constants.ERROR_DESCRIPTION));
				if (json.containsKey(OAuth2Constants.ERROR_URI))
					errorParameters.setErrorUri(json.getString(OAuth2Constants.ERROR_URI));
				if (json.containsKey(OAuth2Constants.STATE))
					errorParameters.setState(json.getString(OAuth2Constants.STATE));
				
				throw new OAuth2ErrorResponseException(response.getStatusCode(), errorParameters);
//				throw new OAuthResponseException("OAuth 2 Error: " + LINE_SEPARATOR + entityBody);
			}
			
			return handleOAuthResponse(json);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new OAuthResponseException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new OAuthResponseException(e);
		}
	}
	
	private void verifyResponseHeader(HttpResponse response, String headerName, String headerValue) throws OAuthResponseException {
		Header header = response.getHeader(headerName);
		if (header == null) {
			throw new OAuthResponseException("No " + headerName + " header found.");
		}
		
		String value = header.getValue();
		if (!value.equals(headerValue) && !value.contains(headerValue)) {
			throw new OAuthResponseException("HTTP response header '" + headerName + "' must contain the value '" + headerValue + "' (currently set to '" + value + "').");
		}
	}

	protected abstract T handleOAuthResponse(JSONObject json) throws OAuthResponseException;
}
