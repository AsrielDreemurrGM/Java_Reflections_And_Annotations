package br.com.eaugusto.reflections.annotations.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.eaugusto.reflections.annotations.dao.generic.GenericSetDAO;
import br.com.eaugusto.reflections.annotations.domain.Client;

/**
 * DAO Implementation For Managing Client Entities Using GenericSetDAO.
 * 
 * <p>
 * This class uses a generic Set-based storage for {@link Client} entities,
 * handling the underlying storage map for entity sets.
 * </p>
 * 
 * It implements basic CRUD operations and updates client data accordingly.
 *
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since May 21, 2025
 */
public class ClientSetDAO extends GenericSetDAO<Client> implements IClientDAO {

	public ClientSetDAO() {
		super();
	}

	@Override
	public Class<Client> getClassType() {
		return Client.class;
	}

	/**
	 * Registers a new entity in the Set.
	 * <p>
	 * If an entity with the same identifier already exists, it will not be added.
	 * 
	 * @param entity the entity to register.
	 * @return {@code true} if the entity was added successfully; {@code false} if
	 *         it was already present.
	 */
	@Override
	public Boolean register(Client entity) {
		Set<Client> entitySet = storage.get(getClassType());
		if (entitySet == null) {
			entitySet = new HashSet<>();
			storage.put(getClassType(), entitySet);
		}
		return entitySet.add(entity);
	}

	/**
	 * Deletes an entity identified by the given value (usually a CPF or product
	 * code).
	 * 
	 * @param value the identifier (CPF or code) of the entity to delete.
	 */
	@Override
	public void delete(String cpf) {
		Set<Client> entitySet = storage.get(getClassType());
		if (entitySet != null) {
			entitySet.removeIf(client -> client.getCodeOrCPF().equals(cpf));
		}
	}

	/**
	 * Updates an existing entity identified by its identifier.
	 * <p>
	 * If the entity is found, its data will be updated using
	 * {@link #updateRegisteredEntityWithNewData(Object, Object)}.
	 * 
	 * @param entity the entity containing updated data.
	 */
	@Override
	public void updateEntity(Client entity) {
		Client registeredEntity = search(entity.getCodeOrCPF());
		if (registeredEntity != null) {
			updateRegisteredEntityWithNewData(entity, registeredEntity);
		}
	}

	/**
	 * Searches for an entity by its identifier (CPF or code).
	 * 
	 * @param value the identifier to search for.
	 * @return the found entity, or {@code null} if not found.
	 */
	@Override
	public Client search(String cpf) {
		Set<Client> entitySet = storage.get(getClassType());
		if (entitySet != null) {
			return entitySet.stream().filter(client -> client.getCodeOrCPF().equals(cpf)).findFirst().orElse(null);
		}
		return null;
	}

	/**
	 * Returns a collection of all entities of the managed type.
	 * 
	 * @return a collection containing all stored entities; empty if none are
	 *         present.
	 */
	@Override
	public Collection<Client> searchAll() {
		Set<Client> entitySet = storage.get(getClassType());
		return entitySet != null ? entitySet : new HashSet<>();
	}

	@Override
	public void updateRegisteredEntityWithNewData(Client entity, Client registeredEntity) {
		registeredEntity.updateWith(entity);
	}
}
