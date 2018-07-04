package ph.inv.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;

import ph.inv.bean.Auditable;
import ph.inv.dao.AuditDao;

@Repository(value="auditDao")
public class AuditDaoImpl implements AuditDao {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Auditable> audits(String classname, RevisionType type) {
		AuditReader a = AuditReaderFactory.get(entityManager);
		AuditQuery aq = null;
		
		try {
			System.out.println("Classname: " + Class.forName(classname));
			/*aq = a.createQuery().forRevisionsOfEntity(Class.forName(classname), false, false);
			aq.add(AuditEntity.revisionNumber().le(100));
			//aq.add(AuditEntity.revisionType().eq(type));
			aq.add(AuditEntity.revisionNumber().maximize());
			aq.addOrder(AuditEntity.revisionNumber().desc());*/
			//aq = a.createQuery().forEntitiesAtRevision(Class.forName(classname), AuditEntity.revisionNumber().maximize());
			System.out.println(AuditEntity.revisionNumber().max().toString());
			System.out.println(AuditEntity.revisionNumber().maximize().computeAggregationInInstanceContext());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return aq.getResultList();
	}
}
