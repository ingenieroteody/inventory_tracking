package ph.inv.service.impl;

import org.springframework.stereotype.Service;

import ph.inv.entity.UserRole;
import ph.inv.service.UserRoleService;

@Service(value="userRoleService")
public class UserRoleServiceImpl extends AbstractServiceImpl<UserRole, Long> implements UserRoleService {

}
