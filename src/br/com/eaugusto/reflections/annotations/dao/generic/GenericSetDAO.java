package br.com.eaugusto.reflections.annotations.dao.generic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.eaugusto.reflections.annotations.domain.Persistable;

/**
 * Abstract generic DAO implementation using an in-memory Map of Sets to store
 * entities.
 * <p>
 * This class provides basic CRUD operations (Create, Read, Update, Delete) for
 * any {@link Persistable} entity type. The storage is organized as:
 *
 * <pre>
 * Map&lt;Class&lt;T&gt;, Set&lt;T&gt;&gt;
 * </pre>
 *
 * Meaning each entity type gets its own Set of entities. Duplicate entries are
 * prevented based on the entity's {@link Object#equals(Object)} and
 * {@link Object#hashCode()} methods.
 * <p>
 * Subclasses must implement:
 * <ul>
 * <li>{@link #getClassType()} to specify the entity type managed</li>
 * <li>{@link #updateRegisteredEntityWithNewData(Object, Object)} to define how
 * entities are updated</li>
 * </ul>
 * 
 * @param <T> The type of entity managed by this DAO (must implement
 *            {@link Persistable}).
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 02, 2025
 */
public abstract class GenericSetDAO<T extends Persistable> implements IGenericDAO<T> {

	protected Map<Class<T>, Set<T>> storage;

	/**
	 * Returns the class type of the entity managed by this DAO.
	 * <p>
	 * This is used as the key for the internal storage Map.
	 * 
	 * @return the Class of the entity type.
	 */
	public abstract Class<T> getClassType();

	/**
	 * Updates the registered entity in the Set with the new entity data.
	 * <p>
	 * Implementations must define how the fields of {@code registeredEntity} are
	 * updated based on the values of {@code newEntity}.
	 * 
	 * @param newEntity        the new entity containing updated data.
	 * @param registeredEntity the currently registered entity to update.
	 */
	public abstract void updateRegisteredEntityWithNewData(T newEntity, T registeredEntity);

	protected GenericSetDAO() {
		if (this.storage == null) {
			this.storage = new HashMap<>();
		}
	}

	@Override
	public Boolean register(T entity) {
		Set<T> entitySet = this.storage.get(getClassType());

		if (entitySet == null) {
			entitySet = new java.util.HashSet<>();
			this.storage.put(getClassType(), entitySet);
		}

		return entitySet.add(entity);
	}

	@Override
	public void delete(String value) {
		Set<T> entitySet = this.storage.get(getClassType());

		if (entitySet == null) {
			return;
		}

		T toRemove = null;

		for (T entity : entitySet) {
			if (entity.getCodeOrCPF().equals(value)) {
				toRemove = entity;
				break;
			}
		}

		if (toRemove != null) {
			entitySet.remove(toRemove);
		}
	}

	@Override
	public void updateEntity(T entity) {
		Set<T> entitySet = this.storage.get(getClassType());

		if (entitySet == null) {
			return;
		}

		for (T registered : entitySet) {
			if (registered.getCodeOrCPF().equals(entity.getCodeOrCPF())) {
				updateRegisteredEntityWithNewData(entity, registered);
				break;
			}
		}
	}

	@Override
	public T search(String value) {
		Set<T> entitySet = this.storage.get(getClassType());

		if (entitySet == null) {
			return null;
		}

		for (T entity : entitySet) {
			if (entity.getCodeOrCPF().equals(value)) {
				return entity;
			}
		}

		return null;
	}

	@Override
	public Collection<T> searchAll() {
		Set<T> entitySet = this.storage.get(getClassType());

		return entitySet != null ? entitySet : java.util.Collections.emptySet();
	}
}
