package br.com.eaugusto.reflections.annotations.domain;

/**
 * Represents an entity that can be persisted and uniquely identified.
 * <p>
 * This interface defines a contract for objects (such as {@code Client} or
 * {@code Product}) that can be stored, retrieved, or manipulated in a
 * persistent context (e.g., a database or memory). The method
 * {@code getCodeOrCPF()} returns a unique identifier, such as a CPF for clients
 * or a code for products.
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 02, 2025
 */
public interface Persistable {

	/**
	 * Returns a unique identifier for the persistable object.
	 * 
	 * @return a string representing the unique ID (e.g., CPF or product code)
	 */
	public String getCodeOrCPF();
}
