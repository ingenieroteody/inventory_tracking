package ph.inv.service;

import java.util.List;

import ph.inv.entity.ItemMovement;

public interface ItemMovementService extends AbstractService<ItemMovement, Long> {
	public List<ItemMovement> trackInventory(Long inventoryId);
}
