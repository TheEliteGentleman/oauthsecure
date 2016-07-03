package za.co.sindi.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;

import za.co.sindi.oauth.client.transport.exception.TransportException;
import za.co.sindi.oauth.client.transport.http.HttpResponse;
import za.co.sindi.oauth.client.transport.http.HttpTransport;
import za.co.sindi.oauth.client.transport.http.impl.DefaultHttpTransport;
import za.co.sindi.oauth.client.transport.http.request.GetHttpRequest;


/**
 * @author Buhake Sindi
 * @since 26 January 2012
 *
 */
public class TransportTest {

	@Test
	public void doTestHttpTransport() {
		HttpTransport transport = new DefaultHttpTransport();
		try {
			HttpResponse response = transport.send(new GetHttpRequest(new URI("http://www.google.com")));
			System.out.println(response.getReasonPhrase());
			Assert.assertEquals(200, response.getStatusCode());
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			Assert.fail(e.getLocalizedMessage());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			Assert.fail(e.getLocalizedMessage());
		} finally {
			try {
				transport.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
