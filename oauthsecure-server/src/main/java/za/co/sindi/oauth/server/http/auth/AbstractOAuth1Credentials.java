/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import za.co.sindi.oauth.core.utils.OAuth1Constants;

/**
 * @author Bienfait Sindi
 * @since 18 January 2016
 *
 */
public abstract class AbstractOAuth1Credentials implements OAuth1Credentials {

	private final String consumerKey;
	private final OAuthSignatureMethod signatureMethod;
	private final Long timestamp;
	private final String nonce;
	private final String signature;
	private final String version;
	
	/**
	 * @param consumerKey
	 * @param signatureMethod
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @param version
	 */
	protected AbstractOAuth1Credentials(String consumerKey, OAuthSignatureMethod signatureMethod, Long timestamp, String nonce, String signature, String version) {
		super();
		if (consumerKey == null || consumerKey.isEmpty()) {
			throw new IllegalArgumentException("No " + OAuth1Constants.OAUTH_CONSUMER_KEY + " was specified.");
		}
		
		if (signatureMethod == null /*|| signatureMethod.isEmpty()*/) {
			throw new IllegalArgumentException("No " + OAuth1Constants.OAUTH_SIGNATURE_METHOD + " was specified.");
		}
		
		if (signatureMethod != OAuthSignatureMethod.PLAINTEXT) {
			if (timestamp == null || timestamp < 0) {
				throw new IllegalArgumentException("No " + OAuth1Constants.OAUTH_TIMESTAMP + " was specified.");
			}
			
			if (nonce == null || nonce.isEmpty()) {
				throw new IllegalArgumentException("No " + OAuth1Constants.OAUTH_NONCE + " was specified.");
			}
		}
		
		if (signature == null || signature.isEmpty()) {
			throw new IllegalArgumentException("No " + OAuth1Constants.OAUTH_SIGNATURE + " was specified.");
		}
		
		if (version != null && !OAuth1Constants.VERSION_1_0.equals(version)) {
			throw new IllegalArgumentException(OAuth1Constants.OAUTH_VERSION + " does not equal to version \"" + OAuth1Constants.VERSION_1_0 + "\".");
		}
		
		this.consumerKey = consumerKey;
		this.signatureMethod = signatureMethod;
		this.timestamp = timestamp;
		this.nonce = nonce;
		this.signature = signature;
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.OAuth1Credentials#getConsumerKey()
	 */
	@Override
	public String getConsumerKey() {
		// TODO Auto-generated method stub
		return consumerKey;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.OAuth1Credentials#getSignatureMethod()
	 */
	@Override
	public OAuthSignatureMethod getSignatureMethod() {
		// TODO Auto-generated method stub
		return signatureMethod;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.OAuth1Credentials#getTimestamp()
	 */
	@Override
	public Long getTimestamp() {
		// TODO Auto-generated method stub
		return timestamp;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.OAuth1Credentials#getNonce()
	 */
	@Override
	public String getNonce() {
		// TODO Auto-generated method stub
		return nonce;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.OAuth1Credentials#getSignature()
	 */
	@Override
	public String getSignature() {
		// TODO Auto-generated method stub
		return signature;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.http.auth.OAuth1Credentials#getVersion()
	 */
	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return version;
	}
}
