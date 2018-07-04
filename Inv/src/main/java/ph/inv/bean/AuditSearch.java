package ph.inv.bean;

import org.hibernate.envers.RevisionType;

public class AuditSearch {

	private String entity;
	
	private RevisionType revisionType;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public RevisionType getRevisionType() {
		return revisionType;
	}

	public void setRevisionType(RevisionType revisionType) {
		this.revisionType = revisionType;
	}
}
