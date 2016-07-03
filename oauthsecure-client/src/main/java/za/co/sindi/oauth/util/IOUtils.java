/**
 * 
 */
package za.co.sindi.oauth.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Buhake Sindi
 * @since 10 February 2012
 *
 */
public class IOUtils {
	
	private static final int DEFAULT_BUFFER_SIZE = 8192;
	
	private IOUtils() {
		//TODO: Do nothing.
	}
	
	public static final String toString(InputStream input, String charset) throws IOException, UnsupportedEncodingException {
		return new String(toByteArray(input, false), charset);
	}

	public static final byte[] toByteArray(InputStream input, boolean nioCopy) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (nioCopy)
			nioCopy(input, baos);
		else
			copy(input, baos);
		
		return baos.toByteArray();
	}
	
	public static final int copy(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int totalBytesRead = 0;
		int bytesRead = 0;
		
		while (-1 != (bytesRead = input.read(buffer))) {
			output.write(buffer, 0, bytesRead);
			totalBytesRead += bytesRead;
		}
		
		return totalBytesRead;
	}
	
	public static final void nioCopy(InputStream input, OutputStream output) throws IOException {
		nioCopy(Channels.newChannel(input), Channels.newChannel(output));
	}
	
	public static final void nioCopy(ReadableByteChannel input, WritableByteChannel output) throws IOException {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
			while (input.read(buffer) != -1) {
				//Flip buffer
				buffer.flip();
				//Write to destination
				output.write(buffer);
				//Compact
				buffer.compact();
			}
			
			//In case we have remainder
			buffer.flip();
			while (buffer.hasRemaining()) {
				//Write to output
				output.write(buffer);
			}
		} finally {
			if (input != null) {
				input.close();
				input = null;
			}
			
			if (output != null) {
				output.close();
				output = null;
			}
		}
	}
}
