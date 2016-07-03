/**
 * 
 */
package za.co.sindi.oauth.server.http.auth;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import za.co.sindi.oauth.server.Path;

/**
 * @author Bienfait Sindi
 * @since 16 February 2016
 *
 */
public class HttpPath implements Path {

	private String absolutePath;
	private String path;
	private Map<String, String[]> parameters = new LinkedHashMap<>();
	
	/**
	 * @param absolutePath
	 */
	public HttpPath(String absolutePath) {
		super();
		if (absolutePath == null || absolutePath.isEmpty()) {
			throw new IllegalArgumentException("No HTTP absolute path was specified.");
		}
		this.absolutePath = absolutePath;
		this.path = absolutePath;
		int parameterStartPos = absolutePath.indexOf(";");
		if (parameterStartPos > -1) {
			path = absolutePath.substring(0, parameterStartPos);
			String[] parameterStrings = absolutePath.substring(parameterStartPos).split(";");
			if (parameterStrings != null) {
				for (String parameterString : parameterStrings) {
					String[] parameterKeyValues = parameterString.split("=");
					if (parameterKeyValues.length == 2) {
						String key = parameterKeyValues[0];
						String[] values = parameterKeyValues[1].split(",");
						
						//Add
						parameters.put(key, values);
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.Path#getAbsolutePath()
	 */
	@Override
	public String getAbsolutePath() {
		// TODO Auto-generated method stub
		return absolutePath;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.Path#getPath()
	 */
	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.Path#gethPathParameters()
	 */
	@Override
	public Map<String, String[]> gethPathParameters() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableMap(parameters);
	}
}
