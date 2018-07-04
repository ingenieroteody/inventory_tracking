package ph.inv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.bean.CurrentInventory;
import ph.inv.dao.InventoryDao;
import ph.inv.entity.Inventory;
import ph.inv.service.InventoryService;

@Service(value="inventoryService")
public class InventoryServiceImpl extends AbstractServiceImpl<Inventory, Long> implements InventoryService{

	@Autowired
	private InventoryDao inventoryDao;
	
	@Override
	public List<Inventory> findBySearch(String searchPhrase, String sortBy) {
		return inventoryDao.findBySearch(searchPhrase, sortBy); 
	}

	public List<Inventory> findByNumberCode(String numberCode) {
		return inventoryDao.findByNumberCode(numberCode);
	}

	public List<CurrentInventory> getCurrentStock() {
		return inventoryDao.getCurrentStock();
	}
}
