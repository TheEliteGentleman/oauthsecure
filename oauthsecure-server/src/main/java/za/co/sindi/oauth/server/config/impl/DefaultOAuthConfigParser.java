/**
 * 
 */
package za.co.sindi.oauth.server.config.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import za.co.sindi.common.utils.PreConditions;
import za.co.sindi.oauth.server.config.ConfigParser;
import za.co.sindi.oauth.server.config.element.BeanConfig;
import za.co.sindi.oauth.server.config.element.BeanReferencePropertyConfig;
import za.co.sindi.oauth.server.config.element.EntryConfig;
import za.co.sindi.oauth.server.config.element.MapPropertyConfig;
import za.co.sindi.oauth.server.config.element.OAuthConfig;
import za.co.sindi.oauth.server.config.element.PropertyConfig;
import za.co.sindi.oauth.server.config.element.RequestConfig;
import za.co.sindi.oauth.server.config.element.ServiceConfig;
import za.co.sindi.oauth.server.config.element.ValuePropertyConfig;
import za.co.sindi.oauth.server.exception.ConfigurationException;
import za.co.sindi.oauth.server.exception.ParseException;

/**
 * @author Bienfait Sindi
 * @since 13 October 2014
 *
 */
public class DefaultOAuthConfigParser implements ConfigParser<OAuthConfig> {

//	private static final Logger LOGGER = Logger.getLogger(DefaultOAuthConfigParser.class.getName());
	private static final String ELEMENT_OAUTH_CONFIG = "oauth-config";
	private static final String ELEMENT_BEANS = "beans";
	private static final String ELEMENT_BEAN = "bean";
	private static final String ELEMENT_ENTRY = "entry";
	private static final String ELEMENT_PROPERTY = "property";
	private static final String ELEMENT_SERVICES = "services";
	private static final String ELEMENT_SERVICE = "service";
	private static final String ELEMENT_REQUEST = "request";
	
//	/* (non-Javadoc)
//	 * @see za.co.sindi.oauth.server.config.Configurator#configure(java.util.Set)
//	 */
//	@Override
//	public OAuthConfig parse(Set<URL> resources) throws ParseException {
//		// TODO Auto-generated method stub
//		if (resources == null) {
//			throw new IllegalArgumentException("No URL resources has been provided.");
//		}
//		
//		OAuthConfig config = new OAuthConfig();
//		for (URL resource : resources) {
//			InputStream resourceStream = null;
//			try {
//				resourceStream = resource.openStream();
//				parse(resourceStream, config);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				throw new ParseException("Error retrieving resource stream of resource " + resource.toExternalForm(), e);
//			} finally {
//				if (resourceStream != null) {
//					try {
//						resourceStream.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						LOGGER.log(Level.WARNING, "Error closing stream of resource " + resource.toExternalForm());
//					}
//					
//					resourceStream = null;
//				}
//			}
//		}
//		
//		return config;
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.config.OAuthConfigParser#parse(java.net.URL)
	 */
	@Override
	public OAuthConfig parse(URL resource) throws ParseException, ConfigurationException {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(resource != null, "URL resource may not be null.");
		
		try {
			return parse(resource.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ParseException("parse()", e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.config.OAuthConfigParser#parse(java.io.InputStream)
	 */
	@Override
	public OAuthConfig parse(InputStream input) throws ParseException, ConfigurationException {
		// TODO Auto-generated method stub
		PreConditions.checkArgument(input != null, "No InputStream provided.");
				
		OAuthConfig config = new OAuthConfig();
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource iSource = new InputSource(input);
			Element rootElement = builder.parse(iSource).getDocumentElement();
			
			//This MUST exist, even if there are no sub-elements
			if (!ELEMENT_OAUTH_CONFIG.equals(rootElement.getTagName())) {
				throw new ParseException("Unknown tag name '" + rootElement.getTagName() + "'. Expected '" + ELEMENT_OAUTH_CONFIG + "'.");
			}
			
			NodeList nodes = rootElement.getElementsByTagName(ELEMENT_BEANS);
			if (nodes != null && nodes.getLength() > 1) {
				throw new ParseException("There must only be 1 element of '" + ELEMENT_BEANS + "'. We found " + nodes.getLength() + " such elements.");
			}
			
			//Get all bean elements
			nodes = ((Element)nodes.item(0)).getElementsByTagName(ELEMENT_BEAN);
			if (nodes != null && nodes.getLength() > 0) {
				for (int i = 0; i < nodes.getLength(); i++) {
					parseBeanElement((Element) nodes.item(i), config);
				}
			}
			
			nodes = rootElement.getElementsByTagName(ELEMENT_SERVICES);
			if (nodes != null && nodes.getLength() > 1) {
				throw new ParseException("There must only be 1 element of '" + ELEMENT_SERVICES + "'. We found " + nodes.getLength() + " such elements.");
			}
			
			//Get all bean elements
			nodes = ((Element)nodes.item(0)).getElementsByTagName(ELEMENT_SERVICE);
			if (nodes != null && nodes.getLength() > 0) {
				for (int i = 0; i < nodes.getLength(); i++) {
					parseServiceElement((Element) nodes.item(i), config);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			throw new ParseException("Error parsing resource.", e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			throw new ParseException("Error parsing resource.", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ParseException("Error parsing resource.", e);
		}
		
		return config;
	}
	
	private void parseBeanElement(Element element, OAuthConfig config) throws ParseException, ConfigurationException {
		BeanConfig bean = new BeanConfig();
		String id = element.getAttribute("id");
		if (id == null || id.isEmpty()) {
			throw new ParseException("No 'id' attribute has been provided for <bean> element.");
		}
		
		String className = element.getAttribute("class");
		if (className == null || className.isEmpty()) {
			throw new ParseException("No 'class' attribute has been provided for <bean> element.");
		}
		
		if (config.findBeanConfig(id) != null) {
			throw new ConfigurationException("A registered bean with id '" + id + "' already exists.");
		}
		
		if (config.findBeanConfigByClassName(className) != null) {
			throw new ConfigurationException("A registered bean with class name '" + className + "' already exists.");
		}
		
		bean.setId(id);
		bean.setClassName(className);
		
		NodeList propertyNodes = element.getElementsByTagName(ELEMENT_PROPERTY);
		if (propertyNodes != null) {
			for (int i = 0; i < propertyNodes.getLength(); i++) {
				parsePropertyElement((Element) propertyNodes.item(i), bean);
			}
		}
		
		config.addBeanConfig(bean);
	}
	
	private void parsePropertyElement(Element element, BeanConfig bean) throws ParseException, ConfigurationException {
		PropertyConfig property = null;
		String propertyName = element.getAttribute("name");
		if (propertyName == null || propertyName.isEmpty()) {
			throw new ParseException("No 'name' attribute has been provided for <property> element.");
		}
		
		String attribute = element.getAttribute("value");
		if (attribute != null && !attribute.isEmpty()) {
			property = new ValuePropertyConfig();
			((ValuePropertyConfig)property).setValue(attribute);
		} else {
			attribute = element.getAttribute("bean-ref");
			if (attribute != null && !attribute.isEmpty()) {
				property = new BeanReferencePropertyConfig();
				((BeanReferencePropertyConfig)property).setBeanReferenceId(attribute);
			} else {
				NodeList entryNodes = element.getElementsByTagName(ELEMENT_ENTRY);
				if (entryNodes != null && entryNodes.getLength() > 0) {
					property = new MapPropertyConfig();
					for (int i = 0; i < entryNodes.getLength(); i++) {
						Element entryElement = (Element) entryNodes.item(i);
						String key = entryElement.getAttribute("key");
						if (key == null || key.isEmpty()) {
							throw new ParseException("No 'key' attribute has been provided for <entry> element (child of '" + propertyName + "' element).");
						}
						
						String value = entryElement.getAttribute("value");
						if (value == null) {
							throw new ParseException("No 'value' attribute has been specified for the <entry> element (child of '" + propertyName + "' element).");
						}
						
						EntryConfig entry = new EntryConfig();
						entry.setKey(key);
						entry.setValue(value);
						
						((MapPropertyConfig)property).addEntryConfig(entry);
					}
				}
			}
		}
		
		if (property == null) {
			throw new ParseException("Could not parse and identify <property> element with name '" + propertyName + "'.");
		}
		
		if (bean.findPropertyConfig(propertyName) != null) {
			throw new ConfigurationException("A registered property with name '" + propertyName + "' already exists.");
		}
		
		bean.addPropertyConfig(property);
	}
	
	private void parseServiceElement(Element element, OAuthConfig config) throws ParseException, ConfigurationException {
		ServiceConfig service = new ServiceConfig();
		String id = element.getAttribute("id");
		if (id == null || id.isEmpty()) {
			throw new ParseException("No 'id' attribute has been provided for <service> element.");
		}
		
		String address = element.getAttribute("address");
//		if (address == null || address.isEmpty()) {
//			throw new ParseException("No 'address' attribute has been provided for <service> element.");
//		}
		
		if (address != null && !address.startsWith("/")) {
			throw new ParseException("'address' attribute must be a relative address (starting with /).");
		}
		
		String beanReferenceId = element.getAttribute("bean-ref");
		if (beanReferenceId == null || beanReferenceId.isEmpty()) {
			throw new ParseException("No 'bean-ref' attribute has been provided for <service> element.");
		}
		
		if (config.findServiceConfigById(id) != null) {
			throw new ConfigurationException("A registered service with id '" + id + "' already exists.");
		}
		
		if (config.findServiceConfigByAddress(address) != null) {
			throw new ConfigurationException("A registered service with address '" + address + "' already exists.");
		}
		
		service.setId(id);
		service.setAddress(address);
		service.setBeanReferenceId(beanReferenceId);
		
		NodeList propertyNodes = element.getElementsByTagName(ELEMENT_REQUEST);
		if (propertyNodes != null) {
			for (int i = 0; i < propertyNodes.getLength(); i++) {
				parseRequestElement((Element) propertyNodes.item(i), service);
			}
		}
		
		config.addServiceConfig(service);
	}
	
	private void parseRequestElement(Element element, ServiceConfig service) throws ParseException, ConfigurationException {
		RequestConfig request = new RequestConfig();
		String method = element.getAttribute("method");
		if (method == null || method.isEmpty()) {
			throw new ParseException("No 'method' attribute has been provided for <request> element.");
		}
		
		String path = element.getAttribute("path");
		if (path == null || path.isEmpty() && (service.getAddress() == null || service.getAddress().isEmpty())) {
			throw new ParseException("No 'path' attribute has been provided for <request> element (also <service id=\"" + service.getId() + "\" has no binding address> .");
		}
		
		String operation = element.getAttribute("operation");
		if (operation == null || operation.isEmpty()) {
			throw new ParseException("No 'operation' attribute has been provided for <request> element.");
		}
		
		if (service.findRequestConfig(method) != null) {
			throw new ConfigurationException("Service (address=" + service.getAddress() + ") already contains a '" + method + "' method.");
		}
		
		request.setMethod(method);
		request.setPath(path);
		request.setOperation(operation);
		service.addRequestConfig(request);
	}
}
