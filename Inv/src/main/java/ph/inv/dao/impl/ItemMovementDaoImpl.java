package ph.inv.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ph.inv.dao.ItemMovementDao;
import ph.inv.entity.ItemMovement;

@Repository(value="itemMovementDao")
public class ItemMovementDaoImpl extends AbstractDaoImpl<ItemMovement,Long> implements ItemMovementDao{

	public ItemMovementDaoImpl() {
		super(ItemMovement.class);
	}

	@Override
	public List<ItemMovement> trackInventory(Long inventoryId) {
		StringBuilder queryBuilder = new StringBuilder("SELECT i FROM ItemMovement i ");
		queryBuilder.append("WHERE i.inventory.id = :inventoryId ");
		queryBuilder.append("ORDER BY id DESC");
		
		TypedQuery<ItemMovement> query = entityManager.createQuery(queryBuilder.toString(), ItemMovement.class);
		query.setParameter("inventoryId", inventoryId);
		return query.getResultList();
	}

}
