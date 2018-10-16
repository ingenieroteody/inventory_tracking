package ph.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.dao.ItemMovementDao;
import ph.inv.entity.ItemMovement;
import ph.inv.service.ItemMovementService;

@Service(value="itemMovementService")
public class ItemMovementServiceImpl extends AbstractServiceImpl<ItemMovement, Long> implements ItemMovementService{

	@Autowired
	public ItemMovementDao itemMovementDao;
	
	@Override
	public List<ItemMovement> trackInventory(Long inventoryId) {
		return itemMovementDao.trackInventory(inventoryId);
	}

}
