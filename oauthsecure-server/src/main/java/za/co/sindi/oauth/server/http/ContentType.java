/**
 * 
 */
package za.co.sindi.oauth.server.http;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;

import za.co.sindi.oauth.core.http.ContentTypeParameterCodec;

/**
 * @author Bienfait Sindi
 * @since 18 February 2016
 *
 */
public class ContentType {

	private static final Pattern TYPE_PATTERN = Pattern.compile("(application|audio|image|message|multipart|text|video|x-[^ ()<>@,;:\\/.\\[\\]]+)");
	private static final String PARAMETER_CHARSET = "charset";
	private static final String WILDCARD = "*";
	
	//Content-Types
	public static final ContentType APPLICATION_X_WWW_FORM_URLENCODED = new ContentType("application", "x-www-form-urlencoded");

	public final static ContentType APPLICATION_JSON = new ContentType("application", "json");
	
	private String type;
	private String subtype;
	private Map<String, String> parameters;
	
	/**
	 * 
	 */
	public ContentType() {
		this(WILDCARD, WILDCARD);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 * @param subtype
	 */
	public ContentType(String type, String subtype) {
		this(type, subtype, (Charset)null);
	}
	
	/**
	 * @param type
	 * @param subtype
	 * @param charset
	 */
	public ContentType(String type, String subtype, Charset charset) {
		this(type, subtype, charset, null);
	}

	/**
	 * @param type
	 * @param subtype
	 * @param parameters
	 */
	public ContentType(String type, String subtype, Map<String, String> parameters) {
		super();
		this.type = type;
		this.subtype = subtype;
		this.parameters = parameters;
	}
	
	/**
	 * @param type
	 * @param subtype
	 * @param charset
	 * @param parameters
	 */
	public ContentType(String type, String subtype, Charset charset, Map<String, String> parameters) {
		super();
		if (subtype == null || subtype.isEmpty()) {
			throw new IllegalArgumentException("Media subtype is MANDATORY.");
		}
		this.type = type;
		this.subtype = subtype;
		Map<String, String> map = parameters != null ? new LinkedHashMap<String, String>(parameters) : new LinkedHashMap<String, String>();
		if (charset != null) {
			map.put(PARAMETER_CHARSET, charset.name());
		}
		this.parameters = copyParameters(map);
	}
	
	private Map<String, String> copyParameters(Map<String, String> parameterMap) {
		if (parameterMap == null) {
			return null;
		}
		
		Map<String, String> map = new TreeMap<>(new Comparator<String>() {

			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareToIgnoreCase(o2);
			}
		});
		
		for (Entry<String, String> entry : parameterMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		
		return Collections.unmodifiableMap(map);
	}
	
	public static ContentType from(String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		
		int semiColonPos = value.indexOf(";");
		String mediaType = "";
		String parameters = null;
		if (semiColonPos > -1) {
			mediaType = value.substring(0, semiColonPos);
			parameters = value.substring(semiColonPos + 1).trim();
		} else {
			mediaType = value;
		}
		
		int typeSeparatorPos = mediaType.indexOf("/");
		if (typeSeparatorPos < 0) {
			throw new IllegalArgumentException("Couldn't determine media type from value '" + value + "'.");
		}
		
		//As Per RFC 2015 - all media type values, subtype values, and parameter names are case insensitive.
		String type = mediaType.substring(0, typeSeparatorPos).toLowerCase();
		String subType = mediaType.substring(typeSeparatorPos + 1);
		
		if (!TYPE_PATTERN.matcher(type).matches()) {
			throw new IllegalArgumentException("Invalid media type '" + type + "'.");
		}
		
		if (parameters == null || parameters.isEmpty()) {
			return new ContentType(type, subType);
		}
		
		return new ContentType(type, subType, new ContentTypeParameterCodec().decode(parameters));
	}
	
	public String getMediaTypeAsString() {
		return type + "/" + subtype;
	}
	
	public ContentType includeCharset(Charset charset) {
		return new ContentType(type, subtype, copyParameters(parameters));
	}
	
	public boolean isWildcardType() {
		return WILDCARD.equals(type);
	}
	
	public boolean isWildcardSubtype() {
		return WILDCARD.equals(subtype);
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the subtype
	 */
	public String getSubtype() {
		return subtype;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, String> getParameters() {
		return parameters;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(type).append("/").append(subtype);
		if (parameters != null && !parameters.isEmpty()) {
			sb.append("; ").append(new ContentTypeParameterCodec().encode(parameters));
		}
		
		return sb.toString();
	}
}
