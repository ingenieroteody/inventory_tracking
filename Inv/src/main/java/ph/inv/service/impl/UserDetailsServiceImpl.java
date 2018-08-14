package ph.inv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ph.inv.dao.UserDao;
import ph.inv.entity.RoleAuthority;
import ph.inv.entity.UserRole;
import ph.inv.service.UserService;

@Service(value="userService")
public class UserDetailsServiceImpl extends AbstractServiceImpl<ph.inv.entity.User, Long> implements UserService { //, AuthenticationProvider

	@Autowired
	UserDao userDao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ph.inv.entity.User userApp = (ph.inv.entity.User) userDao.loadUserByUsername(username);
		return (UserDetails) new User(userApp.getUsername(), userApp.getPassword(), userApp.isEnabled(), userApp.isAccountNonExpired(), userApp.isCredentialsNonExpired(), userApp.isAccountNonLocked(), userApp.getAuthorities());
	}

	/*public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        
        if (!(credentials instanceof String)) {
            return null;
        }
        String password = credentials.toString();
        System.out.println("userDao: " + loadUserByUsername(name));
        ph.inv.entity.User userOptional = (ph.inv.entity.User) loadUserByUsername(name);
        		
        if (userOptional != null) {
            throw new BadCredentialsException("Authentication failed for " + name);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for(UserRole ur : userOptional.getRoles()) {
        	for(RoleAuthority ra: ur.getAuthorities()) {
        		grantedAuthorities.add(new SimpleGrantedAuthority(ra.getAuthority()));
        	}
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
        return auth;
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}*/


}
