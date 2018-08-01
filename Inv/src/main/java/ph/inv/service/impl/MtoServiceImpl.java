package ph.inv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.dao.MtoDao;
import ph.inv.entity.Mto;
import ph.inv.service.MtoService;

@Service(value="mtoService")
public class MtoServiceImpl extends AbstractServiceImpl<Mto, Long> implements MtoService{

	@Autowired
	private MtoDao mtoDao;
}
