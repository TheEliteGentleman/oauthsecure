/**
 * 
 */
package za.co.sindi.oauth.server.validator;

import za.co.sindi.oauth.server.exception.oauth.OAuthValidationException;

/**
 * @author Bienfait Sindi
 * @since 19 Febuary 2016
 *
 */
public interface OAuthValidator<T> {

	public void validate(T value) throws OAuthValidationException;
}
