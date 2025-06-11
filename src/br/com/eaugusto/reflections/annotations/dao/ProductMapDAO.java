package br.com.eaugusto.reflections.annotations.dao;

import br.com.eaugusto.reflections.annotations.dao.generic.GenericMapDAO;
import br.com.eaugusto.reflections.annotations.domain.Product;

/**
 * DAO Implementation For Managing Product Entities Using GenericMapDAO.
 * 
 * <p>
 * This class specializes {@link GenericMapDAO} for {@link Product} entities.
 * </p>
 * 
 * It provides the concrete Class type and updates product fields accordingly.
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 03, 2025
 */
public class ProductMapDAO extends GenericMapDAO<Product> implements IProductDAO {

	public ProductMapDAO() {
		super();
	}

	@Override
	public Class<Product> getClassType() {
		return Product.class;
	}

	@Override
	public void updateRegisteredEntityWithNewData(Product newEntity, Product registeredEntity) {
		registeredEntity.setName(newEntity.getName());
		registeredEntity.setDescription(newEntity.getDescription());
		registeredEntity.setValue(newEntity.getValue());
		registeredEntity.setBrand(newEntity.getBrand());
	}
}
