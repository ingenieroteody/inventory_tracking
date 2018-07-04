package ph.inv.service;

import java.util.List;

import org.hibernate.envers.RevisionType;

import ph.inv.bean.Auditable;

public interface AuditService {

	public List<Auditable> audits(String classname, RevisionType type);
}
