/**
 * 
 */
package za.co.sindi.oauth.server.spi.oauth2.clientreg;

import java.io.Serializable;
import java.net.URI;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.InternetAddress;

/**
 * @author Bienfait Sindi
 * @since 28 March 2016
 *
 */
public class ClientMetadata implements Serializable {

	private Set<URI> redirectURIs;
	
	private TokenEndpointAuthMethod authenticationMethod;
	
	private Set<GrantType> grantTypes;
	
	private Set<ResponseType> responseTypes;
	
	private Map<Locale, String> clientNames;
	
	private Map<Locale, URI> clientURIs;
	
	private URI logoURI;
	
	private Set<String> scopes;
	
	private Set<InternetAddress> contacts;
	
	private Map<Locale, URI> tosURIs;
	
	private Map<Locale, URI> policyURIs;
	
	private Map<Locale, URI> jwksURIs;
	
	private Object jwks;
	
	private String softwareId;
	
	private String softwareVersion;
	
	
}
