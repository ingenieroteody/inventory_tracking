package ph.inv.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Audited
@Table(name="user")
public class User extends BaseEntity implements UserDetails {

	@Column(name="username", columnDefinition="VARCHAR(50)", nullable=false, unique=true)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;

	@Column(name="isEnabled", nullable=false)
	private boolean isEnabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<UserRole> roles;
	
	@Column(name="isAccountNonExpired", nullable=false)
	private boolean isAccountNonExpired;
	
	@Column(name="isAccountNonLocked", nullable=false)
	private boolean isAccountNonLocked;
	
	@Column(name="isCredentialsNonExpired", nullable=false)
	private boolean isCredentialsNonExpired;
	
	public User() {
		isEnabled = true;
		isAccountNonExpired = true;
		isAccountNonLocked = true;
		isCredentialsNonExpired = true;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<RoleAuthority> authorities = new HashSet<RoleAuthority>();
		for(UserRole role : roles) {
			for(RoleAuthority roleAuthority : role.getAuthorities()) {
				authorities.add(roleAuthority);
			}
		}
		return authorities;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
}
