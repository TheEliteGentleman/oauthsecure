/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.auth;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.client.NonceGenerator;
import za.co.sindi.oauth.client.credentials.oauth2.AccessToken;
import za.co.sindi.oauth.client.transport.HttpAuthorization;
import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.http.Header;
import za.co.sindi.oauth.client.transport.http.HttpParameters;
import za.co.sindi.oauth.client.transport.http.HttpRequest;
import za.co.sindi.oauth.core.http.HttpConstants;
import za.co.sindi.oauth.core.http.HttpHeaderParameterCodec;
import za.co.sindi.oauth.core.utils.OAuthSignatureUtil;

/**
 * Implementation of the OAuth 2 draft-ietf-oauth-v2-http-mac-01.
 * 
 * @author Buhake Sindi
 * @since 15 March 2012
 *
 */
public class OAuth2MacAuthorization extends HttpAuthorization {
	
	private static final String NEWLINE = "\n";
	private static final String HMAC_SHA1 = "hmac-sha-1";
	private static final String HMAC_SHA256 = "hmac-sha-256";
	
	private String id;
	private String key;
	private String algorithm;
	private NonceGenerator nonceGenerator;
	private String ext;
	
	/**
	 * @param builder
	 */
	private OAuth2MacAuthorization(AccessToken accessToken, NonceGenerator nonceGenerator, String ext) {
		super();
		if (accessToken == null) {
			throw new IllegalArgumentException("An Access Token may not be null.");
		}
		
		if (!getSchemeName().equalsIgnoreCase(accessToken.getTokenType())) {
			throw new IllegalArgumentException("Invalid token type '" + accessToken.getTokenType() + "' (expected: " + getSchemeName() + ").");
		}
		
		this.nonceGenerator = nonceGenerator;
		id = accessToken.getAccessToken();
		key = accessToken.getAdditionalParameters().get("mac_key");
		algorithm = accessToken.getAdditionalParameters().get("mac_algorithm");
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.auth.OAuthHttpAuthorization#getSchemeName()
	 */
	@Override
	public String getSchemeName() {
		// TODO Auto-generated method stub
		return "MAC";
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.auth.OAuthHttpAuthorization#createAuthorizationString(net.oauth.transport.http.HttpRequest)
	 */
	@Override
	protected String createAuthorizationString(HttpRequest request) throws AuthorizationException {
		// TODO Auto-generated method stub
		if (request == null) {
			throw new IllegalArgumentException("HTTP request may not be null.");
		}
		
		try {
			Header header = request.getHeader(HttpConstants.HEADER_HOST);
			long timestamp = System.currentTimeMillis();
			String nonce = nonceGenerator.generateNonce();
			String normalizedRequestString = generateNormalizedRequestString(timestamp, nonce, header == null ? null : header.getValue(), request.getMethod(), request.getURI());
			
			String authorizationString = null;
			byte[] _key = key.getBytes("UTF-8");
			byte[] data = normalizedRequestString.getBytes("UTF-8");
			if (HMAC_SHA1.equals(algorithm)) {
				authorizationString = OAuthSignatureUtil.generateHmacSha1Signature(_key, data);
			} else if (HMAC_SHA256.equals(algorithm)) {
				authorizationString = OAuthSignatureUtil.generateHmacSha256Signature(_key, data);
			} else {
				throw new AuthorizationException("Unknown algorithm: " + algorithm);
			}
			
			HttpParameters parameters = new HttpParameters();
			parameters.setParameter("id", id);
			parameters.setParameter("ts", timestamp);
			parameters.setParameter("nonce", nonce);
			if (ext != null && !ext.isEmpty()) {
				parameters.setParameter("ext", ext);
			}
			
			parameters.setParameter("mac", authorizationString);
			return parameters.toString(new HttpHeaderParameterCodec());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new AuthorizationException(e);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			throw new AuthorizationException(e);
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			throw new AuthorizationException(e);
		}
	}
	
	/**
	 * Create a Normalized Request String as based on Paragraph 3.2.1 
	 * 
	 * @param timestamp
	 * @param nonce
	 * @param method
	 * @param requestUri
	 * @return
	 * @throws URISyntaxException
	 */
	private String generateNormalizedRequestString(final long timestamp, final String nonce, final String host, final String method, final URI uri) {
		String scheme = uri.getScheme().toLowerCase();
		String hostName = host == null ? uri.getAuthority().toLowerCase() : host.toLowerCase();
		int port = uri.getPort();
		if (port == -1) {
			if (scheme.equals("http")) {
				port = 80;
			} else if (scheme.equals("https")) {
				port = 443;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(timestamp).append(NEWLINE)
		  .append(nonce).append(NEWLINE)
		  .append(method.toUpperCase()).append(NEWLINE)
		  .append(uri.getPath()).append(NEWLINE)
		  .append(hostName).append(NEWLINE)
		  .append(port).append(NEWLINE)
		  .append(ext == null? "":ext).append(NEWLINE);
		
		return sb.toString();
	}
}
