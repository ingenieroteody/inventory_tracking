package ph.inv.dao.impl;

import org.springframework.stereotype.Repository;

import ph.inv.dao.UserRoleDao;
import ph.inv.entity.UserRole;

@Repository(value="userRoleDao")
public class UserRoleDaoImpl extends AbstractDaoImpl<UserRole, Long> implements UserRoleDao {

	public UserRoleDaoImpl() {
		super(UserRole.class);
		// TODO Auto-generated constructor stub
	}

}
