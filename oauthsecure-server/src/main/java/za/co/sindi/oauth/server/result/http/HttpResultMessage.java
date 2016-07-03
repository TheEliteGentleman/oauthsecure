/**
 * 
 */
package za.co.sindi.oauth.server.result.http;

import java.io.IOException;
import java.util.Map.Entry;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.context.OAuthContext;
import za.co.sindi.oauth.server.context.http.HttpRequestContext;
import za.co.sindi.oauth.server.context.http.HttpResponseContext;
import za.co.sindi.oauth.server.exception.ResultException;
import za.co.sindi.oauth.server.result.message.Message;

/**
 * @author Buhake Sindi
 * @since 07 May 2012
 *
 */
public class HttpResultMessage extends AbstractHttpResult {

	private int statusCode;
	private Message message;
	
	/**
	 * @param message
	 */
	public HttpResultMessage(int statusCode, Message message) {
		super();
		PreConditions.checkArgument(message != null, "A message may not be null.");
		
		this.statusCode = statusCode;
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.result.Result#executeResult(za.co.sindi.oauth.server.context.OAuthContext)
	 */
	@Override
	public void executeResult(OAuthContext context) throws ResultException {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(context != null,"No OAuthContext was provided.");
		
		executeResult((HttpRequestContext)context.getContainer().getRequest(), (HttpResponseContext)context.getContainer().getResponse());
	}

	protected void executeResult(HttpRequestContext requestContext, HttpResponseContext responseContext) throws ResultException {
		try {
			if (statusCode < 100) {
				throw new IOException("Invalid HTTP Status Code '" + statusCode + "'.");
			}

			for (Entry<String, String> entry : headersEntrySet()) {
				responseContext.addHeader(entry.getKey(), entry.getValue());
			}

			// Set status
			responseContext.setStatusCode(statusCode);

			if (message != null) {
				// Set Content-Type
				responseContext.addHeader("Content-Type", message.getContentType());
				// Set Content-Length
				responseContext.addHeader("Content-Length", String.valueOf(message.getContentLength()));

				// Write...
				message.writeTo(responseContext.getOutputStream());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ResultException(e);
		}
	}
}
