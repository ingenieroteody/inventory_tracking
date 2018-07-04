package ph.inv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import ph.inv.annotation.Searchable;

@Entity
@Audited
@Table(name="color")
public class Color extends BaseEntity {
	
	@Column(name="name", nullable=false, columnDefinition="VARCHAR(25)")
	@Searchable
	private String name;
	
	@Column(name="code", unique=true, nullable=false,columnDefinition="VARCHAR(15)")
	@Searchable
	private String code;
	
	@Column(name="remarks", columnDefinition="TEXT")
	private String remarks;

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String toString() {
		return "Id: " + getId() + " Name: " + name + " Code: " + code + " Remarks: " + remarks;
	}
}
