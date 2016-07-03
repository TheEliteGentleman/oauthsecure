/**
 * 
 */
package za.co.sindi.oauth.client.transport.http.auth;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.PrivateKey;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import za.co.sindi.codec.StringCodec;
import za.co.sindi.codec.exception.EncodingException;
import za.co.sindi.oauth.client.NonceGenerator;
import za.co.sindi.oauth.client.credentials.oauth1.OAuth1ClientCredentials;
import za.co.sindi.oauth.client.parameters.oauth1.OAuth1HeaderParameterCodec;
import za.co.sindi.oauth.client.parameters.oauth1.OAuth1Parameters;
import za.co.sindi.oauth.client.request.oauth1.enums.OAuthSignature;
import za.co.sindi.oauth.client.request.oauth1.enums.RequestType;
import za.co.sindi.oauth.client.transport.HttpAuthorization;
import za.co.sindi.oauth.client.transport.exception.AuthorizationException;
import za.co.sindi.oauth.client.transport.http.HttpParameters;
import za.co.sindi.oauth.client.transport.http.HttpRequest;
import za.co.sindi.oauth.client.transport.http.util.HttpParameterUtil;
import za.co.sindi.oauth.core.encoding.PercentEncodingCodec;
import za.co.sindi.oauth.core.exceptions.OAuthSignatureException;
import za.co.sindi.oauth.core.http.HttpConstants;
import za.co.sindi.oauth.core.http.HttpQueryParameterCodec;
import za.co.sindi.oauth.core.signers.OAuthHmacSha1Signer;
import za.co.sindi.oauth.core.signers.OAuthPlaintextSigner;
import za.co.sindi.oauth.core.signers.OAuthRsaSha1Signer;
import za.co.sindi.oauth.core.signers.OAuthSigner;
import za.co.sindi.oauth.core.utils.OAuth1Constants;
import za.co.sindi.oauth.core.utils.OAuth1Util;

/**
 * @author Buhake Sindi
 * @since 15 February 2012
 *
 */
public class OAuth1Authorization extends HttpAuthorization {

	private OAuth1AuthorizationBuilder builder;
	private OAuth1Parameters parameters;

	/**
	 * @param builder
	 */
	private OAuth1Authorization(OAuth1AuthorizationBuilder builder) {
		super();
		this.builder = builder;
		parameters = new OAuth1Parameters();
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.auth.OAuthScheme#getOAuthParameters()
	 */
	public OAuth1Parameters getOAuthParameters() {
		// TODO Auto-generated method stub
		return parameters;
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.auth.OAuthHttpAuthorization#getSchemeName()
	 */
	@Override
	public String getSchemeName() {
		// TODO Auto-generated method stub
		return "OAuth";
	}

	/* (non-Javadoc)
	 * @see net.oauth.core.auth.OAuthHttpAuthorization#createAuthorizationString(net.oauth.core.OAuthClientCredentials, net.oauth.transport.http.HttpRequest)
	 */
	@Override
	protected String createAuthorizationString(HttpRequest request) throws AuthorizationException {
		// TODO Auto-generated method stub
		if (builder == null) {
			throw new AuthorizationException("Cannot authenticate. Scheme requires builder for instantiation.");
		}
		
		if (request == null) {
			throw new IllegalArgumentException("No HTTP request provided.");
		}
		
		if (builder.requestType != RequestType.PROTECTED_RESOURCE && !"POST".equals(request.getMethod())) {
			throw new IllegalArgumentException("Invalid HTTP request method for request type " + builder.requestType.toString());
		}
		
		//Authenticate.
		OAuthSigner signer = createSigner();
		
		try {
			parameters.clear();
//			parameters.setRealm(builder.realm);
			parameters.setConsumerKey(builder.clientCredentials.getConsumerKey());
			parameters.setToken(builder.token);
			parameters.setTimestamp(OAuth1Util.getTimestamp());
			parameters.setNonce(builder.nonceGenerator.generateNonce());
			parameters.setVerifier(builder.verifier);
			parameters.setCallback(builder.callback);
			parameters.setSignatureMethod(signer.getSignatureMethod());
			if (builder.includeOAuthVersion) {
				parameters.setVersion();
			}
			
			String baseString = generateSignatureBaseString(request.getMethod(), request.getURI(), parameters, builder.additionalParameters);
			if (logger.isDebugEnabled()) {
				logger.debug("OAuth base string: " + baseString);
			}
			
			parameters.setSignature(signer.sign(baseString));
		} catch (OAuthSignatureException e) {
			// TODO Auto-generated catch block
			throw new AuthorizationException(e);
		} catch (EncodingException e) {
			// TODO Auto-generated catch block
			throw new AuthorizationException(e);
		}
		
		//Needs this as realm doesn't get Percent-Encoded (instead it's quoted-string)
		StringBuilder sb = new StringBuilder();
		if (builder.realm != null && !builder.realm.isEmpty()) {
			sb.append(HttpConstants.REALM)
			  .append("=\"")
			  .append(builder.realm)
			  .append("\",");
		}
		sb.append(new OAuth1HeaderParameterCodec().encode(parameters.toMap()));
		return sb.toString();
	}
	
	private OAuthSigner createSigner() {
		OAuthSigner signer = null;
		
		switch (builder.signature) {
			case HMAC_SHA1 : 
				signer = new OAuthHmacSha1Signer(builder.clientCredentials.getSecret(), builder.tokenSecret);
			break;
			
			case RSA_SHA1 : 
				signer = new OAuthRsaSha1Signer(builder.privateKey);
			break;
			
			case PLAINTEXT :
				signer = new OAuthPlaintextSigner(builder.clientCredentials.getSecret(), builder.tokenSecret);
			break;
			
			default: break;
		}
		
		return signer;
	}
	
	/**
	 * Generate a Signature Base String, as specified on RFC 5849, paragraph 3.4.1.
	 * 
	 * @param requestMethod HTTP request method
	 * @param requestUrl
	 * @param oauthParameters OAuth Parameters
	 * @param requestParameters HTTP request parameters
	 * @return an OAuth base string that will be used for signature
	 * @throws EncodingException 
	 * @throws URISyntaxException 
	 */
	private String generateSignatureBaseString(final String requestMethod, final URI requestUri, final OAuth1Parameters oauthParameters, final Map<String, String[]> requestParameters) throws EncodingException {
		Map<String, String[]> parameters = new LinkedHashMap<String, String[]>();
		
//		int parameterPos = requestUrl.indexOf('?');
//		int fragmentPos = requestUrl.indexOf('#', parameterPos + 1);
//		if (parameterPos > -1) {
//			String queryString = fragmentPos > -1 ? requestUrl.substring(parameterPos + 1, fragmentPos) : requestUrl.substring(parameterPos + 1);
//			HttpParameters httpParameters= HttpParameterUtil.parseQueryString(queryString);
//			if (httpParameters != null && !httpParameters.isEmpty()) {
//				parameters.putAll(httpParameters.toMap());
//			}
//		}
		
		String queryString = requestUri.getQuery();
		HttpParameters httpParameters= HttpParameterUtil.parseQueryString(queryString);
		if (httpParameters != null && !httpParameters.isEmpty()) {
			parameters.putAll(httpParameters.toMap());
		}
		
		//Adding oauth parameters
		for (Entry<String, String> entry : oauthParameters.toMap().entrySet()) {
			if (!OAuth1Constants.OAUTH_SIGNATURE.equals(entry.getKey())) {
				parameters.put(entry.getKey(), new String[] {entry.getValue()});
			}
		}
		
		if (requestParameters != null && !requestParameters.isEmpty()) {
			parameters.putAll(requestParameters);
		}
		
		StringBuilder sb = new StringBuilder();
		StringCodec codec = new PercentEncodingCodec();
		sb.append(codec.encode(requestMethod.toUpperCase())).append("&")
		  .append(codec.encode(normalizeUrl(requestUri))).append("&")
		  .append(codec.encode(normalizeParameters(parameters, codec)));
		
		return sb.toString();
	}
	
	/**
	 * Normalizes URL according to RFC 5849, paragraph 3.4.1.2.
	 * 
	 * @param uri
	 * @return
	 * @throws URISyntaxException 
	 */
	private String normalizeUrl(final URI uri) {
		String scheme = uri.getScheme().toLowerCase();
		String authority = uri.getAuthority().toLowerCase();
		int port = uri.getPort();
		
		StringBuilder sb = new StringBuilder();
		sb.append(scheme)
		  .append("://")
		  .append(authority);
		
		//Port
		if (port != -1 &&
			(("http".equals(scheme) && 80 != port) ||
			 ("https".equals(scheme) && 443 != port))) {
			sb.append(":").append(port);
		}
		
		//Path
		sb.append(uri.getPath());
		return sb.toString();
	}
	
	/**
	 * Normalize parameters according to RFC 5849, paragraph 3.4.1.3.2.
	 * 
	 * @param parameters
	 * @return
	 * @throws EncodingException 
	 */
	private String normalizeParameters(final Map<String, String[]> parameters, final StringCodec codec) throws EncodingException {
		TreeMap<String, TreeSet<String>> sortedTreeMap = new TreeMap<String, TreeSet<String>>();
		
		if (parameters != null) {
			for (Entry<String, String[]> entry : parameters.entrySet()) {
				TreeSet<String> valueSet = new TreeSet<String>();
				
				for (String value : entry.getValue()) {
					valueSet.add(codec.encode(value));
				}
				
				sortedTreeMap.put(codec.encode(entry.getKey()), valueSet);
			}
		}
		
		Map<String, String[]> sortedParameters = new LinkedHashMap<String, String[]>();
		for (Entry<String, TreeSet<String>> entry : sortedTreeMap.entrySet()) {
			TreeSet<String> treeSet = entry.getValue();
			String[] sortedArray = treeSet.toArray(new String[treeSet.size()]);
			sortedParameters.put(entry.getKey(), sortedArray);
		}
		
		return new HttpQueryParameterCodec().encode(sortedParameters);
	}
	
	public static final class OAuth1AuthorizationBuilder {
		
		private RequestType requestType;
		private OAuth1ClientCredentials clientCredentials;
		private String realm;
		private String token;
		private String tokenSecret;
		private String verifier;
		private String callback;
		private NonceGenerator nonceGenerator;
		private OAuthSignature signature;
		private PrivateKey privateKey;
		private Map<String, String[]> additionalParameters;
		private boolean includeOAuthVersion;
		
		/**
		 * @param requestType
		 */
		public OAuth1AuthorizationBuilder(RequestType requestType) {
			super();
			if (requestType == null) {
				throw new IllegalArgumentException("Request type is required.");
			}
			
			this.requestType = requestType;
		}
		
		public OAuth1Authorization build() {
			if (clientCredentials == null) {
				throw new IllegalArgumentException("OAuth1 client credentials is required.");
			}
			
			if (requestType != RequestType.REQUEST_TOKEN) {
				if (token == null || token.isEmpty()) {
					throw new IllegalArgumentException(OAuth1Constants.OAUTH_TOKEN + " is required.");
				}
				
				if (tokenSecret == null || tokenSecret.isEmpty()) {
					throw new IllegalArgumentException(OAuth1Constants.OAUTH_TOKEN_SECRET + " is required.");
				}
				
				if (requestType == RequestType.ACCESS_TOKEN) {
					if (verifier == null || verifier.isEmpty()) {
						throw new IllegalArgumentException(OAuth1Constants.OAUTH_VERIFIER + " is required.");
					}
				}
			} else {
				if (callback == null || callback.isEmpty()) {
					throw new IllegalArgumentException(OAuth1Constants.OAUTH_CALLBACK + " is required (or set " + OAuth1Constants.OAUTH_CALLBACK + " to 'oob')");
				}
			}
			
			if (nonceGenerator == null) {
				throw new IllegalArgumentException("Nonce generator is required.");
			}
			
			if (signature == null) {
				throw new IllegalArgumentException(OAuth1Constants.OAUTH_SIGNATURE_METHOD + " is required.");
			}
			
			if (signature == OAuthSignature.RSA_SHA1 && privateKey == null) {
				throw new IllegalArgumentException("OAuth RSA-SHA1 requires a private key.");
			}
			
			return new OAuth1Authorization(this);
		}

		/**
		 * @param clientCredentials the clientCredentials to set
		 */
		public void setClientCredentials(OAuth1ClientCredentials clientCredentials) {
			this.clientCredentials = clientCredentials;
		}

		/**
		 * @param realm the realm to set
		 */
		public void setRealm(String realm) {
			this.realm = realm;
		}

		/**
		 * @param token the token to set
		 */
		public void setToken(String token) {
			this.token = token;
		}

		/**
		 * @param tokenSecret the tokenSecret to set
		 */
		public void setTokenSecret(String tokenSecret) {
			this.tokenSecret = tokenSecret;
		}

		/**
		 * @param verifier the verifier to set
		 */
		public void setVerifier(String verifier) {
			this.verifier = verifier;
		}

		/**
		 * @param callback the callback to set
		 */
		public void setCallback(String callback) {
			this.callback = callback;
		}

		/**
		 * @param nonceGenerator the nonceGenerator to set
		 */
		public void setNonceGenerator(NonceGenerator nonceGenerator) {
			this.nonceGenerator = nonceGenerator;
		}

		/**
		 * @param signature the signature to set
		 */
		public void setSignature(OAuthSignature signature) {
			this.signature = signature;
		}

		/**
		 * @param privateKey the privateKey to set
		 */
		public void setPrivateKey(PrivateKey privateKey) {
			this.privateKey = privateKey;
		}

		/**
		 * @param additionalParameters the additionalParameters to set
		 */
		public void setAdditionalParameters(Map<String, String[]> additionalParameters) {
			this.additionalParameters = additionalParameters;
		}

		/**
		 * @param includeOAuthVersion the includeOAuthVersion to set
		 */
		public void setIncludeOAuthVersion(boolean includeOAuthVersion) {
			this.includeOAuthVersion = includeOAuthVersion;
		}
	}
}
