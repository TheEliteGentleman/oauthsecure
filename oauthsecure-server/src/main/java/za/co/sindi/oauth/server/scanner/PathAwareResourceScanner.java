/**
 * 
 */
package za.co.sindi.oauth.server.scanner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import za.co.sindi.oauth.server.exception.ScanningException;
import za.co.sindi.oauth.server.resource.Resource;

/**
 * @author Bienfait Sindi
 * @since 28 October 2014
 *
 */
public abstract class PathAwareResourceScanner extends AbstractResourceScanner {

	private Collection<String> resourcePaths;
	
	/**
	 * 
	 */
	protected PathAwareResourceScanner() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param classLoader
	 */
	protected PathAwareResourceScanner(ClassLoader classLoader) {
		super(classLoader);
		// TODO Auto-generated constructor stub
	}

	public void addResourcePath(String resourcePath) {
		if (resourcePath != null && !resourcePath.isEmpty()) {
			if (resourcePaths == null) {
				resourcePaths = new LinkedHashSet<String>();
			}
			
			resourcePaths.add(resourcePath);
		}
	}
	
//	protected String[] getResourcePaths() {
//		if (resourcePaths == null) {
//			return null;
//		}
//		
//		return resourcePaths.toArray(new String[resourcePaths.size()]);
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.scanner.ResourceScanner#scan()
	 */
	@Override
	public Collection<Resource> scan() throws ScanningException {
		// TODO Auto-generated method stub
		if (resourcePaths == null) {
			return null;
		}
		
		Collection<Resource> resources = new ArrayList<Resource>();
		for (String path : resourcePaths) {
			scan(path, resources);
		}
		
		return resources;
	}
	
	protected abstract void scan(String path, Collection<Resource> resources) throws ScanningException;
}
