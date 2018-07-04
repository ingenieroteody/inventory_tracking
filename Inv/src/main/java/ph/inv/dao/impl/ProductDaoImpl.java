package ph.inv.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ph.inv.dao.ProductDao;
import ph.inv.entity.Product;

@Repository(value="productDao")
public class ProductDaoImpl extends AbstractDaoImpl<Product, Long> implements ProductDao {

	public ProductDaoImpl() {
		super(Product.class);
	}

	
	@Override
	public List<Product> findBySearch(String searchPhrase, String sortBy) {
		StringBuilder queryBuilder = new StringBuilder("SELECT p FROM Product p ");
		queryBuilder.append("WHERE p.name LIKE :searchPhrase ");
		queryBuilder.append("OR p.code LIKE :searchPhrase ");
		queryBuilder.append("ORDER BY " + sortBy);
		
		TypedQuery<Product> query = entityManager.createQuery(queryBuilder.toString(), Product.class);
		query.setParameter("searchPhrase", "%"+searchPhrase+"%");
		return query.getResultList();
	}
}
