package ph.inv.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="user_role")
public class UserRole extends BaseEntity {

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userRole", fetch = FetchType.EAGER)
	private Set<RoleAuthority> authorities;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<RoleAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<RoleAuthority> authorities) {
		this.authorities = authorities;
	}
}
