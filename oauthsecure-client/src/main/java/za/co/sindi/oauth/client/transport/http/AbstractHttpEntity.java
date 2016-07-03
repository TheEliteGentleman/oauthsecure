/**
 * 
 */
package za.co.sindi.oauth.client.transport.http;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * @author Buhake Sindi
 * @since 25 January 2012
 *
 */
public abstract class AbstractHttpEntity implements HttpEntity {

	protected final Logger logger = Logger.getLogger(this.getClass());
	private String contentType;
	private byte[] content;
	private String charset;

	/**
	 * @param contentType
	 */
	protected AbstractHttpEntity(String contentType) {
		super();
		this.contentType = contentType;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpEntity#getCharset()
	 */
	@Override
	public String getCharset() {
		// TODO Auto-generated method stub
		return charset;
	}

	/**
	 * @param charset the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * @return the contentType
	 */
	@Override
	public String getContentType() {
		return contentType;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpEntity#getContentLength()
	 */
	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		if (getContent() == null) {
			return -1;
		}
		
		return getContent().length;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpEntity#getContent()
	 */
	@Override
	public byte[] getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see net.oauth.transport.http.HttpEntity#writeTo(java.io.OutputStream)
	 */
	@Override
	public void writeTo(final OutputStream output) throws IOException {
		// TODO Auto-generated method stub
		if (output == null) {
			throw new IllegalArgumentException("OutputStream is null.");
		}
		
		if (getContent() != null) {
			if (logger.isInfoEnabled()) {
				logger.info("Writing " + getContentLength() + " bytes of content to " + output.getClass().getName() + ".");
			}
			
			output.write(getContent());
			output.flush();
		} else {
			if (logger.isInfoEnabled()) {
				logger.info("No content is available to write.");
			}
		}
	}
}
