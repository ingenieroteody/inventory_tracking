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
import ph.inv.entity.SystemCodes;
import ph.inv.entity.User;
import ph.inv.entity.UserRole;
import ph.inv.enums.PositionEnum;
import ph.inv.service.ColorService;
import ph.inv.service.EmployeeService;
import ph.inv.service.ProductService;
import ph.inv.service.RoleAuthorityService;
import ph.inv.service.SystemCodesService;
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
	
	@Autowired
	private SystemCodesService systemCodesService;
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent e) {
		
		initializeUser();
		
		initializeStaff();
		
		initializeSystemCodes();
		
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
	}
	
	private void initializeUser() {
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
			roleAuthority.setAuthority("ADMIN");
			roleAuthority.setUserRole(userRole);
			roleAuthorityService.save(roleAuthority);
			
			User userStaff = new User();
			userStaff.setUsername("meann");
			userStaff.setPassword(encode.encode("meannpassword"));
			userService.save(userStaff);
			
			UserRole userRoleStaff = new UserRole();
			userRoleStaff.setUser(userStaff);
			userRoleService.save(userRoleStaff);
			
			RoleAuthority roleAuthorityStaff = new RoleAuthority();
			roleAuthorityStaff.setDescription("Post MTO");
			roleAuthorityStaff.setAuthority("STAFF");
			roleAuthorityStaff.setUserRole(userRoleStaff);
			roleAuthorityService.save(roleAuthorityStaff);
		}		
	}
	
	private void initializeStaff() {
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
	
			Employee riza = new Employee();
			riza.setFirstname("Riza");
			riza.setLastname("");
			riza.setPosition(PositionEnum.SEWER);
			employeeService.save(riza);
			
			Employee helen = new Employee();
			helen.setFirstname("Helen");
			helen.setLastname("");
			helen.setPosition(PositionEnum.SEWER);
			employeeService.save(riza);
			
			Employee michael = new Employee();
			michael.setFirstname("Michael");
			michael.setLastname("Hipolito");
			michael.setPosition(PositionEnum.CUTTER);
			employeeService.save(michael);
			
			LOG.info("Default employees are inserted");
		} else {
			LOG.info("employee table initialization skipped");
		}
	}

	private void initializeSystemCodes() {

		if(systemCodesService.findAll().size() == 0) {
			//Sizes
			final String DRESS_SIZE = "DRESS_SIZE";
			SystemCodes small = new SystemCodes();
			small.setCategory(DRESS_SIZE);
			small.setKey("SMALL");
			small.setValue("Small");
			small.setOrdinal(1);
			systemCodesService.save(small);
			
			SystemCodes medium = new SystemCodes();
			medium.setCategory(DRESS_SIZE);
			medium.setKey("MEDIUM");
			medium.setValue("Medium");
			medium.setOrdinal(2);
			systemCodesService.save(medium);
			
			SystemCodes large = new SystemCodes();
			large.setCategory(DRESS_SIZE);
			large.setKey("LARGE");
			large.setValue("Large");
			large.setOrdinal(3);
			systemCodesService.save(large);
			
			SystemCodes large1 = new SystemCodes();
			large1.setCategory(DRESS_SIZE);
			large1.setKey("LARGE1");
			large1.setValue("Large 1");
			large1.setOrdinal(4);
			systemCodesService.save(large1);
			
			SystemCodes large2 = new SystemCodes();
			large2.setCategory(DRESS_SIZE);
			large2.setKey("LARGE2");
			large2.setValue("Large 2");
			large2.setOrdinal(5);
			systemCodesService.save(large2);
			
			//Status
			final String INVENTORY_STATUS = "INVENTORY_STATUS";
			SystemCodes inStock = new SystemCodes();
			inStock.setCategory(INVENTORY_STATUS);
			inStock.setKey("IN_STOCK");
			inStock.setValue("In Stock");
			inStock.setOrdinal(1);
			systemCodesService.save(inStock);
	
			SystemCodes delivered = new SystemCodes();
			delivered.setCategory(INVENTORY_STATUS);
			delivered.setKey("DELIVERED");
			delivered.setValue("Delivered");
			delivered.setOrdinal(2);
			systemCodesService.save(delivered);
			
			SystemCodes change = new SystemCodes();
			change.setCategory(INVENTORY_STATUS);
			change.setKey("CHANGE_ITEM");
			change.setValue("Change item");
			change.setOrdinal(3);
			systemCodesService.save(change);
			
			SystemCodes returnItem = new SystemCodes();
			returnItem.setCategory(INVENTORY_STATUS);
			returnItem.setKey("RETURN_ITEM");
			returnItem.setValue("Return item");
			returnItem.setOrdinal(4);
			systemCodesService.save(returnItem);
			
			SystemCodes sold = new SystemCodes();
			sold.setCategory(INVENTORY_STATUS);
			sold.setKey("SOLD");
			sold.setValue("Sold");
			sold.setOrdinal(5);
			systemCodesService.save(sold);
			
			//Stores
			final String STORE_BRANCHES = "STORE_BRANCHES";
			SystemCodes glorietta = new SystemCodes();
			glorietta.setCategory(STORE_BRANCHES);
			glorietta.setKey("GLORIETTA");
			glorietta.setValue("Glorietta");
			glorietta.setOrdinal(1);
			systemCodesService.save(glorietta);
			
			SystemCodes atc = new SystemCodes();
			atc.setCategory(STORE_BRANCHES);
			atc.setKey("ATC");
			atc.setValue("Alabang Town Center");
			atc.setOrdinal(2);
			systemCodesService.save(atc);
			
			SystemCodes ermita = new SystemCodes();
			ermita.setCategory(STORE_BRANCHES);
			ermita.setKey("ERMITA");
			ermita.setValue("Ermita");
			ermita.setOrdinal(3);
			systemCodesService.save(ermita);
		}
	}
}
