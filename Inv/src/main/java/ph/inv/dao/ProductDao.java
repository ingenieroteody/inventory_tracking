package ph.inv.dao;

import java.util.List;

import ph.inv.entity.Product;

public interface ProductDao extends AbstractDao<Product, Long> {

	public List<Product> findBySearch(String searchPhrase, String sortBy);
}
