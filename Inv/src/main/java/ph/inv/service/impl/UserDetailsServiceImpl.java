package ph.inv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ph.inv.dao.UserDao;
import ph.inv.service.UserService;

@Service(value="userService")
public class UserDetailsServiceImpl extends AbstractServiceImpl<ph.inv.entity.User, Long> implements UserService {

	@Autowired
	UserDao userDao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ph.inv.entity.User userApp = (ph.inv.entity.User) userDao.loadUserByUsername(username);
		User user = new User(userApp.getUsername(), userApp.getPassword(), userApp.isEnabled(), userApp.isAccountNonExpired(), userApp.isCredentialsNonExpired(), userApp.isAccountNonLocked(), userApp.getAuthorities());
		
		return user;
	}


}
