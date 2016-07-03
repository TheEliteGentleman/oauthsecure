/**
 * 
 */
package za.co.sindi.oauth.server.scanner.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;

import za.co.sindi.oauth.server.exception.ScanningException;
import za.co.sindi.oauth.server.resource.Resource;
import za.co.sindi.oauth.server.resource.impl.FileResource;
import za.co.sindi.oauth.server.scanner.PathAwareResourceScanner;

/**
 * @author Bienfait Sindi
 * @since 28 October 2014
 *
 */
public class ClassLoaderResourceScanner extends PathAwareResourceScanner {

//	private final ClassLoader classLoader;
	
	/**
	 * 
	 */
	public ClassLoaderResourceScanner() {
		this(null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param classLoader
	 */
	public ClassLoaderResourceScanner(ClassLoader classLoader) {
		super(classLoader);
//		this.classLoader = classLoader != null ? classLoader : ApplicationUtils.getClassLoader();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.scanner.PathAwareResourceScanner#scan(java.lang.String, java.util.Collection)
	 */
	@Override
	protected void scan(String path, Collection<Resource> resources) throws ScanningException {
		// TODO Auto-generated method stub
		try {
//			Enumeration<URL> enumResources = classLoader.getResources(path);
			Enumeration<URL> enumResources = getClassLoader().getResources(path);
			if (enumResources != null) {
				while (enumResources.hasMoreElements()) {
					URL resource = enumResources.nextElement();
					File file = new File(resource.getPath());
					if (file.isDirectory()) {
						scan(file.getAbsolutePath(), resources);
					} else {
						resources.add(new FileResource(file));
					}
//					resources.add(new URLResource(resource));
//					scan(resource.getPath(), resources);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ScanningException("Error while scanning for resources.", e);
		}
	}
}
