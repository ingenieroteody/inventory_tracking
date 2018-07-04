package ph.inv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import ph.inv.annotation.Searchable;
import ph.inv.enums.SizeEnum;

@Entity
@Audited
@Table(name="product")
public class Product extends BaseEntity {
	
	@Column(name="name")
	@Searchable
	private String name;

	@Column(name="code")
	@Searchable
	private String code;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
