package ph.inv.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;

import ph.inv.bean.CurrentInventory;
import ph.inv.dao.InventoryDao;
import ph.inv.entity.Inventory;
import ph.inv.enums.RtwStatusEnum;
import ph.inv.enums.SizeEnum;

@Repository(value="inventoryDao")
public class InventoryDaoImpl extends AbstractDaoImpl<Inventory, Long> implements InventoryDao{

	public InventoryDaoImpl() {
		super(Inventory.class);
	}

	@Override
	public List<Inventory> findBySearch(String searchPhrase, String sortBy) {
		StringBuilder queryBuilder = new StringBuilder("SELECT i FROM Inventory i ");
		queryBuilder.append("INNER JOIN i.product p ");
		queryBuilder.append("INNER JOIN i.color c ");
		queryBuilder.append("WHERE i.numberCode LIKE :searchPhrase ");
		queryBuilder.append("OR i.date LIKE :searchPhrase ");
		queryBuilder.append("OR p.name LIKE :searchPhrase ");
		queryBuilder.append("OR c.name LIKE :searchPhrase ");
		queryBuilder.append("ORDER BY " + sortBy);
		
		TypedQuery<Inventory> query = entityManager.createQuery(queryBuilder.toString(), Inventory.class);
		query.setParameter("searchPhrase", "%"+searchPhrase+"%");
		return query.getResultList();
	}

	public List<Inventory> findByNumberCode(String numberCode) {
		StringBuilder queryBuilder = new StringBuilder("SELECT i FROM Inventory i ");
		queryBuilder.append("WHERE i.numberCode = :numberCode");
		
		TypedQuery<Inventory> query = entityManager.createQuery(queryBuilder.toString(), Inventory.class);
		query.setParameter("numberCode", numberCode);
		return query.getResultList();
	}

	public List<CurrentInventory> getCurrentStock() {
		List<CurrentInventory> currentInventory = new LinkedList<CurrentInventory>();
		StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(i.numberCode), p.code, p.name, s.value, c.name, ss.value FROM Inventory i ");
		queryBuilder.append("INNER JOIN i.product p ");
		queryBuilder.append("INNER JOIN i.color c ");
		queryBuilder.append("INNER JOIN i.status s ");
		queryBuilder.append("INNER JOIN i.size ss ");
		queryBuilder.append("GROUP BY i.numberCode, p.code, p.name, s.value, c.name, ss.value HAVING s.value = :status");
	
		Query query = entityManager.createQuery(queryBuilder.toString());
		query.setParameter("status", "In Stock");
		
		@SuppressWarnings("unchecked")
		List<Object []> queries = query.getResultList();
		
		for(Object[] q : queries) {
			CurrentInventory i = new CurrentInventory();
			i.setQuantity((Long) q[0]);
			i.setItemCode((String) q[1]);
			i.setItemName((String) q[2] + " - " + (String) q[3]);
			i.setItemStatus((RtwStatusEnum) q[4]);
			i.setColorName((String) q[5]);
			i.setItemSize((SizeEnum) q[6]);
			currentInventory.add(i);
		}
		
		
		return currentInventory;
	}

	public List<Object []> getAuditTrail(Long id) {
		
		AuditQuery auditQuery= getAuditReader().createQuery().forRevisionsOfEntity(Inventory.class, false, true);
		auditQuery.add(AuditEntity.id().eq(id));

		return auditQuery.getResultList();
	}
}
