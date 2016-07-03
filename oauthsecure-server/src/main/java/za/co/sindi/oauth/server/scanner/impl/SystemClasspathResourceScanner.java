/**
 * 
 */
package za.co.sindi.oauth.server.scanner.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.oauth.server.exception.ScanningException;
import za.co.sindi.oauth.server.resource.Resource;
import za.co.sindi.oauth.server.resource.impl.FileResource;
import za.co.sindi.oauth.server.scanner.AbstractResourceScanner;

/**
 * @author Bienfait Sindi
 * @since 27 October 2014
 *
 */
public class SystemClasspathResourceScanner extends AbstractResourceScanner {

	private static final Logger LOGGER = Logger.getLogger(SystemClasspathResourceScanner.class.getName());
	private static final String PATH_SEPARATOR = File.pathSeparator;
	private static final String FILE_SEPARATOR = File.separator;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.scanner.ResourceScanner#scan()
	 */
	@Override
	public Collection<Resource> scan() throws ScanningException {
		// TODO Auto-generated method stub
		Collection<Resource> resources = new ArrayList<Resource>();
		
		String classpath = System.getProperty("java.class.path");
		if (classpath != null) {
			String[] classpaths = classpath.split(PATH_SEPARATOR);
			for (String path : classpaths) {
				//Scan root path first
				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Scanning " + path + " folder.");
				}
				
				scan(path, resources);
			}
		}
		
		return resources;
	}
	
	private void scan(String resourcePath, Collection<Resource> resources) throws ScanningException {
		try {
			File file = new File(resourcePath);
			if (!file.exists()) {
				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Path '" + resourcePath + "' doesn't exist. Skipping...");
				}
				
				return;
			}

			if (file.isDirectory()) {
				for (String dir : file.list()) {
					scan(file.getCanonicalPath() + FILE_SEPARATOR + dir, resources);
				}
			} else {
				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Adding file '" + resourcePath + "' to resource collection.");
				}
				
				resources.add(new FileResource(file));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ScanningException("Error during scanning.", e);
		}
	}
}
