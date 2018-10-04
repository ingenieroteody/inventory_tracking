package ph.inv.service;

import java.util.List;

import ph.inv.entity.SystemCodes;

public interface SystemCodesService extends AbstractService<SystemCodes, Long> {

	public List<SystemCodes> loadByCategory(String category);
	
	public SystemCodes loadByCategoryAndKey(String category, String key);
}
