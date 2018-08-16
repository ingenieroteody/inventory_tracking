package ph.inv.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ph.inv.entity.RoleAuthority;
import ph.inv.entity.User;
import ph.inv.entity.UserRole;
import ph.inv.service.UserService;

@Component("userAuthenticationProvider")
public class UserAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserService userService;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			String name = authentication.getName();
		    Object credentials = authentication.getCredentials();
		    
		    if (!(credentials instanceof String)) {
		        return null;
		    }
		    String password = credentials.toString();
		    UserDetails userOptional = userService.loadUserByUsername(name);
		    		
		    if (userOptional != null) {
		        throw new BadCredentialsException("Authentication failed for " + name);
		    }
		
		    List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		    for(GrantedAuthority ra : userOptional.getAuthorities()) {
		    		grantedAuthorities.add(new SimpleGrantedAuthority(ra.getAuthority()));
		    }
		Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
	    return auth;
	}

	public boolean supports(Class<?> authentication) {
			return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
