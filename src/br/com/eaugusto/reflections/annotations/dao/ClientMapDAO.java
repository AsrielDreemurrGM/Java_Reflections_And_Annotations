package br.com.eaugusto.reflections.annotations.dao;

import br.com.eaugusto.reflections.annotations.dao.generic.GenericMapDAO;
import br.com.eaugusto.reflections.annotations.domain.Client;

/**
 * DAO Implementation For Managing Client Entities Using GenericMapDAO.
 * 
 * <p>
 * This class specializes {@link GenericMapDAO} for {@link Client} entities.
 * </p>
 * 
 * It provides the concrete Class type and how to update client data.
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since May 21, 2025
 */
public class ClientMapDAO extends GenericMapDAO<Client> implements IClientDAO {

	public ClientMapDAO() {
		super();
	}

	@Override
	public Class<Client> getClassType() {
		return Client.class;
	}

	@Override
	public void updateRegisteredEntityWithNewData(Client newClientInformation, Client registeredClient) {
		registeredClient.updateWith(newClientInformation);
	}
}
