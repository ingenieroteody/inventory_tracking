package ph.inv.service.impl;

import java.util.List;

import org.hibernate.envers.RevisionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.bean.Auditable;
import ph.inv.dao.AuditDao;
import ph.inv.service.AuditService;

@Service(value="auditService")
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditDao auditDao;
	
	public List<Auditable> audits(String classname, RevisionType type) {
		return auditDao.audits(classname,type);
	}

}
