package br.com.eaugusto.reflections.annotations.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates which method should be used to retrieve the unique identifier of a
 * field annotated with this annotation.
 * 
 * <p>
 * This annotation must be applied to a field in a class that implements
 * {@link br.com.eaugusto.reflections.annotations.domain.Persistable}.
 * </p>
 * 
 * <p>
 * The value of this annotation should match the name of a no-argument public
 * method (usually a getter) that returns the unique identifier for the object
 * as a {@link String}.
 * </p>
 * 
 * <p>
 * Example:
 * </p>
 *
 * <pre>
 * @code
 * KeyType("getCpf")
 * private String cpf;
 * </pre>
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since Jun 11, 2025
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface KeyType {

	String value();
}
