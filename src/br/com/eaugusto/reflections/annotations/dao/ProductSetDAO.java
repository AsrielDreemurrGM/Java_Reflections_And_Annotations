package br.com.eaugusto.reflections.annotations.dao;

import br.com.eaugusto.reflections.annotations.dao.generic.GenericSetDAO;
import br.com.eaugusto.reflections.annotations.domain.Product;

/**
 * DAO Implementation For Managing Product Entities Using GenericSetDAO.
 * 
 * <p>
 * This class specializes {@link GenericSetDAO} for {@link Product} entities.
 * </p>
 * 
 * It provides the concrete Class type and updates product fields accordingly.
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 06, 2025
 */
public class ProductSetDAO extends GenericSetDAO<Product> implements IProductDAO {

	public ProductSetDAO() {
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
