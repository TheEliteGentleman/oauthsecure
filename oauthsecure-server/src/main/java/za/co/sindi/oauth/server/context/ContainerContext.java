/**
 * 
 */
package za.co.sindi.oauth.server.context;

/**
 * @author Bienfait Sindi
 * @since 23 November 2014
 *
 */
public interface ContainerContext extends Context {

	public ApplicationContext getApplication();
	public RequestContext getRequest();
	public ResponseContext getResponse();
	public void forward(String location) throws Exception;
	public void include(String location) throws Exception;
}
