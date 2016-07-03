/**
 * 
 */
package za.co.sindi.oauth.server.config;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.common.utils.Annotations;
import za.co.sindi.common.utils.Classes;
import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.annotation.Path;
import za.co.sindi.oauth.server.annotation.Request;
import za.co.sindi.oauth.server.annotation.Service;
import za.co.sindi.oauth.server.config.element.BeanConfig;
import za.co.sindi.oauth.server.config.element.OAuthConfig;
import za.co.sindi.oauth.server.config.element.RequestConfig;
import za.co.sindi.oauth.server.config.element.ServiceConfig;
import za.co.sindi.oauth.server.config.impl.DefaultOAuthConfigParser;
import za.co.sindi.oauth.server.exception.ConfigurationException;
import za.co.sindi.oauth.server.exception.ParseException;
import za.co.sindi.oauth.server.exception.ScanningException;
import za.co.sindi.oauth.server.resource.Resource;
import za.co.sindi.oauth.server.scanner.PathAwareResourceScanner;
import za.co.sindi.oauth.server.scanner.ResourceScanner;
import za.co.sindi.oauth.server.scanner.impl.ClassLoaderResourceScanner;
import za.co.sindi.oauth.server.util.Globals;


/**
 * @author Bienfait Sindi
 * @since 23 October 2014
 *
 */
public abstract class AbstractOAuthConfigFactory implements OAuthConfigFactory {

	protected static final String OAUTH_META_INF_XML_FILE = "META-INF/" + Globals.OAUTH_CONFIG_FILENAME;
	protected static final String JAVA_JAR_SUFFIX = ".jar";
	protected static final String JAVA_CLASS_SUFFIX = ".class";
	
	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private ConfigParser<OAuthConfig> configParser;
	private Collection<ResourceScanner> resourceScanners;
//	private AnnotationScanner annotationScanner;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.config.OAuthConfigFactory#setOAuthConfigParser(za.co.sindi.oauth.server.config.OAuthConfigParser)
	 */
	@Override
	public void setOAuthConfigParser(ConfigParser<OAuthConfig> configParser) {
		// TODO Auto-generated method stub
		this.configParser = configParser;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.config.OAuthConfigFactory#addResourceScanner(za.co.sindi.oauth.server.scanner.ResourceScanner)
	 */
	@Override
	public void addResourceScanner(ResourceScanner resourceScanner) {
		// TODO Auto-generated method stub
		if (resourceScanner != null) {
			if (resourceScanners == null) {
				resourceScanners = new ArrayList<ResourceScanner>();
			}
			
			resourceScanners.add(resourceScanner);
		}
	}

//	/* (non-Javadoc)
//	 * @see za.co.sindi.oauth.server.config.OAuthConfigFactory#setAnnotationScanner(za.co.sindi.oauth.server.scanner.AnnotationScanner)
//	 */
//	@Override
//	public void setAnnotationScanner(AnnotationScanner annotationScanner) {
//		// TODO Auto-generated method stub
//		this.annotationScanner = annotationScanner;
//		if (this.annotationScanner != null) {
//			//Just in case
//			this.annotationScanner.shouldScanClass(true);
//			this.annotationScanner.shouldScanFields(false);
//			this.annotationScanner.shouldScanMethods(true);
//		}
//	}

	protected ConfigParser<OAuthConfig> getOAuthConfigParser() {
		return configParser;
	}

//	/**
//	 * @return the annotationScanner
//	 */
//	protected AnnotationScanner getAnnotationScanner() {
//		return annotationScanner;
//	}

	/**
	 * @return the resourceScanners
	 */
	protected Collection<ResourceScanner> getResourceScanners() {
		return resourceScanners;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.config.OAuthConfigFactory#create()
	 */
	@Override
	public OAuthConfig create() throws ConfigurationException {
		// TODO Auto-generated method stub
		if (getOAuthConfigParser() == null) {
			if (LOGGER.isLoggable(Level.INFO)) {
				LOGGER.log(Level.INFO, "No OAuthConfigParser instance provided. Defaulting to " + DefaultOAuthConfigParser.class.getName());
			}
			
			setOAuthConfigParser(new DefaultOAuthConfigParser());
		}
		
		if (resourceScanners == null || resourceScanners.isEmpty()) {
			loadClassPathScanner();
		}
		
		OAuthConfig oauthConfig = new OAuthConfig();
		scanOAuthConfigFromResources(oauthConfig);
//		scanOAuthConfigFromAnnotatedClasses(oauthConfig);
		
		return oauthConfig;
	}
	
	private void loadClassPathScanner() {
		PathAwareResourceScanner scanner = new ClassLoaderResourceScanner();
		String[] classPaths = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
		if (classPaths != null && classPaths.length > 0) {
			for (String classPath : classPaths) {
				scanner.addResourcePath(classPath);
			}
		}
		addResourceScanner(scanner);
	}
	
	private void scanOAuthConfigFromResources(OAuthConfig oauthConfig) throws ConfigurationException {
		String path = null;
		
		try {
			for (ResourceScanner scanner : resourceScanners) {
				Collection<Resource> resources = scanner.scan();
				if (resources != null && !resources.isEmpty()) {
					for (Resource resource : resources) {
						path = resource.getPath();
						if (path.endsWith(OAUTH_META_INF_XML_FILE)) {
							mergeOAuthConfig(configParser.parse(resource.getInputStream()), oauthConfig);
						} else if (path.endsWith(JAVA_JAR_SUFFIX)) {
//							if (LOGGER.isLoggable(Level.INFO)) {
//								LOGGER.info("Scanning JAR " + resource.getPath());
//							}
							scanJarEntries(oauthConfig, new URL("jar", "", -1, resource.getFile().toURI().toURL().toString() + "!/"), scanner.getClassLoader());
						} else if (path.endsWith(JAVA_CLASS_SUFFIX)) {
							String className = null;
							
							if (path.contains("WEB-INF/classes/")) {
								className = path.substring(path.indexOf("WEB-INF/classes/") + 16);
							} else if (path.contains("classes/")) {
								className = path.substring(path.indexOf("classes/") + 8);
							} else {
								className = path;
							}
							
							className = cleanClassName(className, JAVA_CLASS_SUFFIX);
							if (!shouldScanClass(className)) continue;
//							if (LOGGER.isLoggable(Level.INFO)) {
//								LOGGER.info("Scanning class " + cleanClassName(className, JAVA_CLASS_SUFFIX) + " for annotation @Service (resource path=" + resource.getPath());
//							}
							Class<?> clazz = Classes.getClass(scanner.getClassLoader(), className, false);
							scanOAuthConfigFromAnnotatedClasses(oauthConfig, clazz);
						}
					}
				}
			}
		} catch (ScanningException e) {
			// TODO Auto-generated catch block
			throw new ConfigurationException("Scanning failed (file path = " + path + ").", e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new ConfigurationException("Parsing failed (file path = " + path + ").", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ConfigurationException("Error getting input stream from resource (file path = " + path + ").", e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ConfigurationException("Class Not Found (file path = " + path + ").", e);
		}
	}
	
	private void scanJarEntries(OAuthConfig oauthConfig, URL jarURL, ClassLoader classLoader) throws IOException, ConfigurationException {
		JarURLConnection jarConn = (JarURLConnection) jarURL.openConnection();
		JarFile jarFile = jarConn.getJarFile();
		Enumeration<JarEntry> entries = jarFile.entries();
//		URLClassLoader classLoader = new URLClassLoader(new URL[] {jarURL});
		while (entries != null && entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			if (entry.isDirectory()) continue;
			
			String name = entry.getName();
//			if (name.contains("/")) continue;
			
			if (name.endsWith(JAVA_CLASS_SUFFIX)) {
				try {
					String className = cleanClassName(entry.getName(), JAVA_CLASS_SUFFIX);
					if (!shouldScanClass(className)) continue;
//					if (LOGGER.isLoggable(Level.INFO)) {
//						LOGGER.info("Scanning class " + cleanClassName(entry.getName(), JAVA_CLASS_SUFFIX) + " for annotation @Service (JAR entry=" + entry.getName());
//					}
					
					Class<?> clazz = Classes.getClass(classLoader, className, false);
					scanOAuthConfigFromAnnotatedClasses(oauthConfig, clazz);
				} catch (ClassNotFoundException | LinkageError e) {
//					if (LOGGER.isLoggable(Level.WARNING)) {
//						LOGGER.warning("Could not find class " + cleanClassName(entry.getName(), JAVA_CLASS_SUFFIX));
//					}
					//Ignore...
				}
			}
		}
		
		//Finally
//		classLoader.close();
	}
	
	private void scanOAuthConfigFromAnnotatedClasses(OAuthConfig oauthConfig, Class<?> clazz) throws ConfigurationException {
		PreConditions.checkArgument(clazz != null, "No class type provided.");
		
		Service serviceAnnotation = Annotations.findAnnotation(clazz, Service.class);
		if (serviceAnnotation != null) {
			//Register bean first...
			if (oauthConfig.findBeanConfigByClassName(clazz.getName()) != null) {
				throw new ConfigurationException("Bean " + clazz.getName() + " is already registered.");
			}
			
			//Does service exist?
			String serviceName = serviceAnnotation.value();
			if (serviceName == null || serviceName.isEmpty()) {
				serviceName = Character.toLowerCase(clazz.getName().charAt(0)) + clazz.getName().substring(1);
			}
			if (oauthConfig.findServiceConfigById(serviceName) != null) {
				throw new ConfigurationException("Service id '" + serviceName + "' already exists.");
			}
			
			BeanConfig beanConfig = new BeanConfig();
			beanConfig.setClassName(clazz.getName());
			beanConfig.setId(serviceName);
			oauthConfig.addBeanConfig(beanConfig);
			
			//Register service.
			Path pathAnnotation = Annotations.findAnnotation(clazz, Path.class);
			ServiceConfig serviceConfig = new ServiceConfig();
			serviceConfig.setId(serviceName);
			serviceConfig.setBeanReferenceId(beanConfig.getId());
			if (pathAnnotation != null) {
				serviceConfig.setAddress(pathAnnotation.value());
			}
			
			//Create RequestConfig
			Method[] methods = Annotations.findMethods(clazz, Request.class);
			if (methods != null) {
				for (Method method : methods) {
					Request requestAnnotation = Annotations.findAnnotation(method, Request.class);
					
					for (String requestMethod : requestAnnotation.method()) { 
						if (serviceConfig.findRequestConfig(requestMethod) != null) {
							throw new ConfigurationException("A request method '" + requestMethod + "' has already been registered. I suggest modifying method '" + method + "'.");
						}
						
						RequestConfig requestConfig = new RequestConfig();
						requestConfig.setMethod(requestMethod);
						requestConfig.setOperation(method.getName());
						requestConfig.setPath(requestAnnotation.path());
						
						//Register
						serviceConfig.addRequestConfig(requestConfig);
					}
				}
			}
			
			oauthConfig.addServiceConfig(serviceConfig);
		}	
	}
	
	private void mergeOAuthConfig(OAuthConfig sourceConfig, OAuthConfig destinationConfig) {
		PreConditions.checkArgument(sourceConfig != null, "Source OAuthConfig may not be null.");
		PreConditions.checkArgument(destinationConfig != null, "Destination OAuthConfig may not be null.");
		
		for (BeanConfig sourceBeanConfig : sourceConfig.getBeanConfigs()) {
			if (destinationConfig.findBeanConfig(sourceBeanConfig.getId()) == null && destinationConfig.findBeanConfigByClassName(sourceBeanConfig.getClassName()) == null) {
				destinationConfig.addBeanConfig(sourceBeanConfig);
			}
		}
		
		for (ServiceConfig sourceServiceConfig : sourceConfig.getServiceConfigs()) {
			if (destinationConfig.findServiceConfigById(sourceServiceConfig.getId()) == null && destinationConfig.findServiceConfigByAddress(sourceServiceConfig.getAddress()) == null) {
				destinationConfig.addServiceConfig(sourceServiceConfig);
			}
		}
	}
	
	private String cleanClassName(String className, String endSuffix) {
		return className.substring(0, className.length() - endSuffix.length()).replace("\\", ".").replace("/", ".");
	}
	
	private boolean shouldScanClass(String className) {
		return (!className.startsWith("java.") && 
				!className.startsWith("javax.") && 
				!className.startsWith("com.sun."));
	}
}
