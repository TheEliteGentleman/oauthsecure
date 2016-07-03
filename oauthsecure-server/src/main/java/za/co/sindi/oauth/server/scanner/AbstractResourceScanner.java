/**
 * 
 */
package za.co.sindi.oauth.server.scanner;

import java.util.logging.Logger;

import za.co.sindi.oauth.server.util.ApplicationUtils;

/**
 * @author Bienfait Sindi
 * @since 08 January 2016
 *
 */
public abstract class AbstractResourceScanner implements ResourceScanner {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private ClassLoader classLoader;
	
	/**
	 * 
	 */
	protected AbstractResourceScanner() {
		this(null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param classLoader
	 */
	protected AbstractResourceScanner(ClassLoader classLoader) {
		super();
		this.classLoader = classLoader;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.scanner.ResourceScanner#getClassLoader()
	 */
	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		if (classLoader == null) {
			classLoader = ApplicationUtils.getClassLoader();
		}
		
		return classLoader;
	}
}
