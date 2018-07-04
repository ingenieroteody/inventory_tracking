package ph.inv.dao;

import java.util.List;

import org.hibernate.envers.RevisionType;

import ph.inv.bean.Auditable;

public interface AuditDao {

	public List<Auditable> audits(String classname, RevisionType type);
}
