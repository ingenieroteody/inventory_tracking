package ph.inv.dao.impl;

import org.springframework.stereotype.Repository;

import ph.inv.dao.RoleAuthorityDao;
import ph.inv.entity.RoleAuthority;

@Repository(value="roleAuthorityDao")
public class RoleAuthorityDaoImpl extends AbstractDaoImpl<RoleAuthority, Long> implements RoleAuthorityDao {

	public RoleAuthorityDaoImpl() {
		super(RoleAuthority.class);
		// TODO Auto-generated constructor stub
	}

}
