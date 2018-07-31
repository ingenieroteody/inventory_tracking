package ph.inv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import ph.inv.annotation.Searchable;
import ph.inv.enums.PositionEnum;

@Entity
@Audited
@Table(name="employee")
public class Employee extends BaseEntity {

	@Column(name="firstname")
	@Searchable
	private String firstname;
	
	@Column(name="lastname")
	@Searchable
	private String lastname;
	
	@Enumerated(EnumType.STRING)
	@Column(name="position", length=25)
	private PositionEnum position;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public PositionEnum getPosition() {
		return position;
	}

	public void setPosition(PositionEnum position) {
		this.position = position;
	}
	
	public String getCompleteName() {
		return lastname + ", " + firstname;
	}
	
	public String toString() {
		return "Firstname: " + firstname + " Lastname: " + lastname + " Position: " + position.getValue();
	}
}
