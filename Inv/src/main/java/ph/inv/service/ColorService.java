package ph.inv.service;

import ph.inv.entity.Color;

public interface ColorService extends AbstractService<Color, Long>{

	public Color findByCode(String code);
}
