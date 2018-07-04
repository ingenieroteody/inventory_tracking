package ph.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.dao.ProductDao;
import ph.inv.entity.Product;
import ph.inv.service.ProductService;

@Service(value="productService")
public class ProductServiceImpl extends AbstractServiceImpl<Product, Long>  implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> findBySearch(String searchPhrase, String sortBy) {
		return productDao.findBySearch(searchPhrase, sortBy);
	}

}
