package ph.inv.dao;

import org.springframework.security.core.userdetails.UserDetailsService;

import ph.inv.entity.User;

public interface UserDao extends AbstractDao<User, Long>, UserDetailsService{

}
