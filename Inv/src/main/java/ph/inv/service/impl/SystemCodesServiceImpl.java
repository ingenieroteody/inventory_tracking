package ph.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.dao.SystemCodesDao;
import ph.inv.entity.SystemCodes;
import ph.inv.service.SystemCodesService;

@Service(value="systemCodesService")
public class SystemCodesServiceImpl extends AbstractServiceImpl<SystemCodes, Long> implements SystemCodesService {

	@Autowired
	private SystemCodesDao dao;
	
	@Override
	public List<SystemCodes> loadByCategory(String category) {
		return dao.loadByCategory(category);
	}

	@Override
	public SystemCodes loadByCategoryAndKey(String category, String key) {
		return dao.loadByCategoryAndKey(category, key);
	}

}
