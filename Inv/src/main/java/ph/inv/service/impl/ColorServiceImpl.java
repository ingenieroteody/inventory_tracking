package ph.inv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.dao.ColorDao;
import ph.inv.entity.Color;
import ph.inv.service.ColorService;

@Service(value="colorService")
public class ColorServiceImpl extends AbstractServiceImpl<Color, Long> implements ColorService{

	@Autowired
	ColorDao colorDao;
	
	public Color findByCode(String code) {
		return colorDao.findByCode(code);
	}

}
