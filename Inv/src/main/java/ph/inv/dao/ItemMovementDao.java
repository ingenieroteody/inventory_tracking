package ph.inv.dao;

import java.util.List;

import ph.inv.entity.ItemMovement;

public interface ItemMovementDao extends AbstractDao<ItemMovement,Long> {

	public List<ItemMovement> trackInventory(Long inventoryId);
}
