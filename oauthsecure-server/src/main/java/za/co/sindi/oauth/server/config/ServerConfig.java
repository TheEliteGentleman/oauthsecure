/**
 * 
 */
package za.co.sindi.oauth.server.config;


/**
 * @author Bienfait Sindi
 * @since 11 October 2014
 *
 */
public interface ServerConfig {

	/** 
     * Returns the filter-name of this filter as defined in the deployment
     * descriptor. 
     */
    public String getConfigName();

    /**
     * Returns a <code>String</code> containing the value of the 
     * named initialization parameter, or <code>null</code> if 
     * the initialization parameter does not exist.
     *
     * @param name a <code>String</code> specifying the name of the
     * initialization parameter
     *
     * @return a <code>String</code> containing the value of the
     * initialization parameter, or <code>null</code> if 
     * the initialization parameter does not exist
     */
    public String getInitParameter(String name);

    /**
     * Returns the names of the server's initialization parameters
     * as an array of <code>String</code> objects, 
     * or (possibly) a <code>null</code> if the server has
     * no initialization parameters.
     *
     * @return an <code>Enumeration</code> of <code>String</code> objects
     * containing the names of the filter's initialization parameters
     */
    public String[] getInitParameterNames();
}
