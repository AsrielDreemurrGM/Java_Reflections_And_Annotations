package br.com.eaugusto.reflections.annotations.dao.generic;

import java.util.Collection;

import br.com.eaugusto.reflections.annotations.domain.Persistable;

/**
 * Generic interface for Data Access Objects (DAO) using a unique identifier.
 *
 * @param <T> The entity type which must implement Persistable.
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 02, 2025
 */
public interface IGenericDAO<T extends Persistable> {

	/**
	 * Registers a new entity.
	 *
	 * @param entity The entity to register.
	 * @return true if the entity was successfully registered; false if it already
	 *         exists.
	 */
	public Boolean register(T entity);

	/**
	 * Deletes an entity by its unique identifier.
	 *
	 * @param value The CPF or code of the entity to delete.
	 */
	public void delete(String value);

	/**
	 * Updates an existing entity with new data.
	 *
	 * @param entity The entity containing updated information.
	 */
	public void updateEntity(T entity);

	/**
	 * Searches for an entity by its unique identifier.
	 *
	 * @param value The CPF or code to search.
	 * @return The found entity, or null if not found.
	 */
	public T search(String value);

	/**
	 * Returns all registered entities.
	 *
	 * @return A collection of all stored entities.
	 */
	public Collection<T> searchAll();
}
