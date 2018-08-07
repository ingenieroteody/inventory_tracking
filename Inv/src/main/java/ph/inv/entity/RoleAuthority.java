package ph.inv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Audited
@Table(name="role_authority")
public class RoleAuthority extends BaseEntity implements GrantedAuthority{

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_role_id", nullable = true)
	private UserRole userRole;
	
	@Column(name="authority", nullable=false)
	private String authority;
	
	@Column(name="description", nullable=false)
	private String description;

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
