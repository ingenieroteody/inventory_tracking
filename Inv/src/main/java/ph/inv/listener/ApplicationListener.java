package ph.inv.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ph.inv.entity.Color;
import ph.inv.entity.Employee;
import ph.inv.entity.Product;
import ph.inv.entity.RoleAuthority;
import ph.inv.entity.User;
import ph.inv.entity.UserRole;
import ph.inv.enums.PositionEnum;
import ph.inv.service.ColorService;
import ph.inv.service.EmployeeService;
import ph.inv.service.ProductService;
import ph.inv.service.RoleAuthorityService;
import ph.inv.service.UserRoleService;
import ph.inv.service.UserService;

@Component
public class ApplicationListener {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationListener.class); 
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleAuthorityService roleAuthorityService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent e) {
		
		if(employeeService.findAll().size() == 0) {
			Employee rosalina = new Employee();
			rosalina.setFirstname("Rosalina");
			rosalina.setLastname("Sarmiento");
			rosalina.setPosition(PositionEnum.PATTERN_CUT_AND_SEW);
			employeeService.save(rosalina);
			
			Employee nenita = new Employee();
			nenita.setFirstname("Nenita");
			nenita.setLastname("Esguerra");
			nenita.setPosition(PositionEnum.SEWER);
			employeeService.save(nenita);
			
			Employee michael = new Employee();
			michael.setFirstname("Michael");
			michael.setLastname("Hipolito");
			michael.setPosition(PositionEnum.CUTTER);
			employeeService.save(michael);
			
			LOG.info("Default employees are inserted");
		} else {
			LOG.info("employee table initialization skipped");
		}
		
		if(colorService.findAll().size() == 0) {
			Color blue = new Color();
			blue.setCode("B001");
			blue.setName("Blue");
			colorService.save(blue);
			
			Color red = new Color();
			red.setCode("R001");
			red.setName("Red");
			colorService.save(red);
			
			Color green = new Color();
			green.setCode("G001");
			green.setName("Green");
			colorService.save(green);
			
			LOG.info("Default colors are inserted");
		} else {
			LOG.info("color table initialization skipped");
		}
		
		if(productService.findAll().size() == 0) {
			Product ivy = new Product();
			ivy.setCode("IVY001");
			ivy.setName("Ivy");
			productService.save(ivy);
			
			Product ysa = new Product();
			ysa.setCode("YSA001");
			ysa.setName("Ysa");
			productService.save(ysa);
			
			Product pat = new Product();
			pat.setCode("PAT001");
			pat.setName("Pat");
			productService.save(pat);
			
			LOG.info("Default products are inserted");
		} else {
			LOG.info("product table initialization skipped");
		}
		

		
		

		
		if(userService.findAll().size() == 0){
			User user = new User();
			user.setUsername("ydoet");
			BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
			user.setPassword(encode.encode("ingeniero.teody"));
			userService.save(user);
			
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRoleService.save(userRole);
			
			RoleAuthority roleAuthority = new RoleAuthority();
			roleAuthority.setDescription("Can do anything");
			roleAuthority.setName("Super");
			roleAuthority.setUserRole(userRole);
			roleAuthorityService.save(roleAuthority);
		}
	}
}
