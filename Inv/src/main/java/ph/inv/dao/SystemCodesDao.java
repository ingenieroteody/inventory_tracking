package ph.inv.dao;

import java.util.List;

import ph.inv.entity.SystemCodes;

public interface SystemCodesDao {

	public List<SystemCodes> loadByCategory(String category);
	
	public SystemCodes loadByCategoryAndKey(String category, String key);
}
