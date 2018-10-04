package ph.inv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.Fetch;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(name="item_movement")
public class ItemMovement extends BaseEntity {

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="inventory_id", nullable=false)
	private Inventory inventory;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	private Date date;
	
	@Column(name="remarks")
	private String remarks;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_replaced_id", nullable=true)
	private Inventory itemReplaced;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id", nullable=false)
	private SystemCodes status;
	
	public ItemMovement() {
		// TODO Auto-generated constructor stub
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Date getDate() {
		return date;
	}

	public String getRemarks() {
		return remarks;
	}

	public Inventory getItemReplaced() {
		return itemReplaced;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setItemReplaced(Inventory itemReplaced) {
		this.itemReplaced = itemReplaced;
	}

	public SystemCodes getStatus() {
		return status;
	}

	public void setStatus(SystemCodes status) {
		this.status = status;
	}
}
