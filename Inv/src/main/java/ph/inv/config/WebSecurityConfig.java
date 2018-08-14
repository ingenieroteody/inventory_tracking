package ph.inv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ph.inv.service.UserService;
import ph.inv.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private UserService userService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder()); //.authenticationProvider(new UserDetailsServiceImpl())
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
			.antMatchers("/css/**","/js/**","/webjars/**").permitAll()
			.antMatchers("/inventory/**").hasRole("ADMIN")
			.antMatchers("/mto/**").hasRole("ADMIN")
			.antMatchers("/product/**").hasRole("ADMIN")
			.antMatchers("/color/**").hasRole("ADMIN")
			.antMatchers("/employee/**").hasRole("ADMIN")
			.antMatchers("/customer/**").hasRole("ADMIN")
			.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
		.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll()
		.and()
		.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler);
		
	}
	
	/*@Override
	public void configure(WebSecurity web) throws Exception {
		web.expressionHandler(new DefaultWebSecurityExpressionHandler(){
			@Override
			protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
					FilterInvocation fi) {
				WebSecurityExpressionRoot root = (WebSecurityExpressionRoot) super.createSecurityExpressionRoot(authentication, fi);
				root.setDefaultRolePrefix("");
				return root;
			}
		});
	};*/
	
	
}
