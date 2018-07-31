package ph.inv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.dao.MTODao;
import ph.inv.entity.MTO;
import ph.inv.service.MTOService;

@Service(value="mtoService")
public class MTOServiceImpl extends AbstractServiceImpl<MTO, Long> implements MTOService{

	@Autowired
	private MTODao mtoDao;
}
