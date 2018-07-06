package ph.inv.dao;

import java.util.Date;
import java.util.List;

import ph.inv.bean.CurrentInventory;
import ph.inv.entity.Inventory;

public interface InventoryDao extends AbstractDao<Inventory, Long>{

	public List<Inventory> findBySearch(String searchPhrase, String sortBy);
	
	public List<Inventory> findByNumberCode(String numberCode);
	
	public List<CurrentInventory> getCurrentStock();
	
	public List<Object []> getAuditTrail(Long id);
}
