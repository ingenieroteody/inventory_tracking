package ph.inv.dao;

import ph.inv.entity.Color;

public interface ColorDao extends AbstractDao<Color, Long>{
	public Color findByCode(String code);
}
