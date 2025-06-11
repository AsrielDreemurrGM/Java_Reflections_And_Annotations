package br.com.eaugusto.reflections.annotations.dao;

import br.com.eaugusto.reflections.annotations.dao.generic.IGenericDAO;
import br.com.eaugusto.reflections.annotations.domain.Product;

/**
 * DAO Interface For Product Entities.
 * 
 * <p>
 * Extends the generic DAO interface for operations on {@link Product} entities.
 * </p>
 * 
 * @author Eduardo Augusto (https://github.com/AsrielDreemurrGM/)
 * @since June 02, 2025
 */
public interface IProductDAO extends IGenericDAO<Product> {

}
