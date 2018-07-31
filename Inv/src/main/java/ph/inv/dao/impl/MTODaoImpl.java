package ph.inv.dao.impl;

import org.springframework.stereotype.Repository;

import ph.inv.dao.MTODao;
import ph.inv.entity.MTO;

@Repository(value="mtoDao")
public class MTODaoImpl extends AbstractDaoImpl<MTO, Long> implements MTODao{

	public MTODaoImpl() {
		super(MTO.class);
	}

}
