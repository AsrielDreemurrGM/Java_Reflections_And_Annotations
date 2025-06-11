package br.com.eaugusto.reflections.annotations.dao.generic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.eaugusto.reflections.annotations.domain.Persistable;

/**
 * Abstract Generic DAO Implementation Using Nested Maps As Storage.
 * 
 * <p>
 * This class provides basic CRUD operations for entities of type {@code T} that
 * implement {@link Persistable}. It manages a two-level Map: an outer Map from
 * Class<T> to inner Maps, and inner Maps from unique ID strings to entities.
 * </p>
 * 
 * <p>
 * Subclasses must specify the entity Class type and implement how to update an
 * existing registered entity with new data.
 * </p>
 *
 * @param <T> The type of persistable entity managed by this DAO.
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 02, 2025
 */
public abstract class GenericMapDAO<T extends Persistable> implements IGenericDAO<T> {

	/**
	 * Storage map: associates each entity class with a map of entities by their ID.
	 */
	protected Map<Class<T>, Map<String, T>> storage;

	/**
	 * Returns the class object representing the entity type managed.
	 * 
	 * @return Class of type T
	 */
	public abstract Class<T> getClassType();

	/**
	 * Updates the data of a registered entity with values from a new entity.
	 * 
	 * @param newEntity        The entity containing new data.
	 * @param registeredEntity The currently registered entity to update.
	 */
	public abstract void updateRegisteredEntityWithNewData(T newEntity, T registeredEntity);

	/**
	 * Constructor initializes the storage map and ensures inner map for the entity
	 * class exists.
	 */
	protected GenericMapDAO() {
		this.storage = new HashMap<>();
		storage.computeIfAbsent(getClassType(), entityClass -> new HashMap<>());
	}

	@Override
	public Boolean register(T entity) {
		Map<String, T> entityMap = storage.get(getClassType());
		if (entityMap.containsKey(entity.getCodeOrCPF())) {
			return false;
		}
		entityMap.put(entity.getCodeOrCPF(), entity);
		return true;
	}

	@Override
	public void delete(String identifier) {
		Map<String, T> entityMap = storage.get(getClassType());
		T registeredEntity = entityMap.get(identifier);

		if (registeredEntity != null) {
			entityMap.remove(identifier);
		}
	}

	@Override
	public void updateEntity(T entity) {
		Map<String, T> entityMap = storage.get(getClassType());
		T registeredEntity = entityMap.get(entity.getCodeOrCPF());

		if (registeredEntity != null) {
			updateRegisteredEntityWithNewData(entity, registeredEntity);
		}
	}

	@Override
	public T search(String identifier) {
		Map<String, T> entityMap = storage.get(getClassType());
		return entityMap.get(identifier);
	}

	@Override
	public Collection<T> searchAll() {
		Map<String, T> entityMap = storage.get(getClassType());
		return entityMap.values();
	}
}
