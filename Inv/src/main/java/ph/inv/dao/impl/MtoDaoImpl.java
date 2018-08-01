package ph.inv.dao.impl;

import org.springframework.stereotype.Repository;

import ph.inv.dao.MtoDao;
import ph.inv.entity.Mto;

@Repository(value="mtoDao")
public class MtoDaoImpl extends AbstractDaoImpl<Mto, Long> implements MtoDao{

	public MtoDaoImpl() {
		super(Mto.class);
	}

}
