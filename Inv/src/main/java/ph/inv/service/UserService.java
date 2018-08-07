package ph.inv.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ph.inv.entity.User;

public interface UserService extends AbstractService<User, Long>, UserDetailsService{

}
