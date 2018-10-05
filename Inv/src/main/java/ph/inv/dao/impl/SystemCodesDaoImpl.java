package ph.inv.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ph.inv.dao.SystemCodesDao;
import ph.inv.entity.SystemCodes;

@Repository(value="systemCodesDao")
public class SystemCodesDaoImpl extends AbstractDaoImpl<SystemCodes,Long> implements SystemCodesDao {

	public SystemCodesDaoImpl() {
		super(SystemCodes.class);
	}

	public List<SystemCodes> loadByCategory(String category) {
		StringBuilder queryBuilder = new StringBuilder("SELECT s FROM SystemCodes s ");
		queryBuilder.append("WHERE s.category= :category");
		
		TypedQuery<SystemCodes> query = entityManager.createQuery(queryBuilder.toString(), SystemCodes.class);
		query.setParameter("category", category);
		return query.getResultList();
	}
	
	public SystemCodes loadByCategoryAndKey(String category, String key) {
		StringBuilder queryBuilder = new StringBuilder("SELECT s FROM SystemCodes s ");
		queryBuilder.append("WHERE s.category= :category ");
		queryBuilder.append("AND s.key= :key");
		
		TypedQuery<SystemCodes> query = entityManager.createQuery(queryBuilder.toString(), SystemCodes.class);
		query.setParameter("category", category);
		query.setParameter("key", key);
		return query.getSingleResult();
		
	}

}
