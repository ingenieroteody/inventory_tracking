package ph.inv.service;

import java.util.List;

import ph.inv.entity.Product;

public interface ProductService extends AbstractService<Product, Long>{
	public List<Product> findBySearch(String searchPhrase, String sortBy);
}
