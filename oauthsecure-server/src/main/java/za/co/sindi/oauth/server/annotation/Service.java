/**
 * 
 */
package za.co.sindi.oauth.server.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The <em>Service</em> annotation describes that your class is a {@link za.co.sindi.oauth.core.service.Service} (without the developer extending it).
 * <br />The <code>value</code> of the Service is the name of the service. If none is provided, the class name (decapitalised) is used.
 * 
 * @author Buhake Sindi
 * @since 23 March 2012
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service {
	
	/**
	 * Service name.
	 * @return
	 */
	String value() default "";
//	Path[] paths() default {};
}
