package ph.inv.service;

import java.util.List;

import ph.inv.bean.CurrentInventory;
import ph.inv.entity.Inventory;

public interface InventoryService extends AbstractService<Inventory, Long>{

	public List<Inventory> findBySearch(String searchPhrase, String sortBy);
	
	public List<Inventory> findByNumberCode(String numberCode);
	
	public List<CurrentInventory> getCurrentStock();
}
